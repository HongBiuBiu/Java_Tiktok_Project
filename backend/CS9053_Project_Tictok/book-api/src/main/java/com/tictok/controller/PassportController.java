package com.tictok.controller;

import com.tictok.base.BaseInfoProperties;
import com.tictok.bo.RegistLoginBO;
import com.tictok.grace.result.GraceJSONResult;
import com.tictok.grace.result.ResponseStatusEnum;
import com.tictok.pojo.Users;
import com.tictok.service.UserService;
import com.tictok.utils.IPUtil;
import com.tictok.utils.MyInfo;
import com.tictok.utils.SMSUtils;
import com.tictok.vo.UsersVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Api(tags = "PassportController passport api")
@RequestMapping("passport")
@RestController
public class PassportController extends BaseInfoProperties {

    @Autowired
    private SMSUtils smsUtils;

    @Autowired
    private UserService userService;

    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(@RequestParam String mobile,
                                      HttpServletRequest request) throws Exception {

        if (StringUtils.isBlank(mobile)) {
            return GraceJSONResult.ok();
        }

        String userIp = IPUtil.getRequestIp(request);
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp, userIp);

        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
        smsUtils.sendSMS(MyInfo.getMobile(), code);
        log.info(code);

        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);

        return GraceJSONResult.ok();
    }

    @PostMapping("login")
    public GraceJSONResult login(@Valid @RequestBody RegistLoginBO registLoginBO,
                                 HttpServletRequest request) throws Exception {

        String mobile = registLoginBO.getMobile();
        String code = registLoginBO.getSmsCode();

        String redisCode = redis.get(MOBILE_SMSCODE + ":" + mobile);
        if (StringUtils.isBlank(redisCode) || !redisCode.equalsIgnoreCase(code)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }

        Users user = userService.queryMobileIsExist(mobile);
        if (user == null) {
            user = userService.createUser(mobile);
        }

        String uToken = UUID.randomUUID().toString();
        redis.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);

        redis.del(MOBILE_SMSCODE + ":" + mobile);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user, usersVO);
        usersVO.setUserToken(uToken);

        return GraceJSONResult.ok(usersVO);
    }


    @PostMapping("logout")
    public GraceJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request) throws Exception {

        redis.del(REDIS_USER_TOKEN + ":" + userId);

        return GraceJSONResult.ok();
    }
}
