package com.tictok.controller;

import com.tictok.base.BaseInfoProperties;
import com.tictok.grace.result.GraceJSONResult;
import com.tictok.grace.result.ResponseStatusEnum;
import com.tictok.pojo.Users;
import com.tictok.service.FansService;
import com.tictok.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "FansController fan api")
@RequestMapping("fans")
@RestController
public class FansController extends BaseInfoProperties {

    @Autowired
    private UserService userService;
    @Autowired
    private FansService fansService;

    @PostMapping("follow")
    public GraceJSONResult follow(@RequestParam String myId,
                                  @RequestParam String vlogerId) {

        if (StringUtils.isBlank(myId) || StringUtils.isBlank(vlogerId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SYSTEM_ERROR);
        }

        if (myId.equalsIgnoreCase(vlogerId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SYSTEM_RESPONSE_NO_INFO);
        }

        Users vloger = userService.getUser(vlogerId);
        Users myInfo = userService.getUser(myId);

        if (myInfo == null || vloger == null) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SYSTEM_RESPONSE_NO_INFO);
        }

        fansService.doFollow(myId, vlogerId);

        redis.increment(REDIS_MY_FOLLOWS_COUNTS + ":" + myId, 1);
        redis.increment(REDIS_MY_FANS_COUNTS + ":" + vlogerId, 1);

        redis.set(REDIS_FANS_AND_VLOGGER_RELATIONSHIP + ":" + myId + ":" + vlogerId, "1");

        return GraceJSONResult.ok();
    }

    @PostMapping("cancel")
    public GraceJSONResult cancel(@RequestParam String myId,
                                  @RequestParam String vlogerId) {

        fansService.doCancel(myId, vlogerId);

        redis.decrement(REDIS_MY_FOLLOWS_COUNTS + ":" + myId, 1);
        redis.decrement(REDIS_MY_FANS_COUNTS + ":" + vlogerId, 1);

        redis.del(REDIS_FANS_AND_VLOGGER_RELATIONSHIP + ":" + myId + ":" + vlogerId);

        return GraceJSONResult.ok();
    }

    @GetMapping("queryDoIFollowVloger")
    public GraceJSONResult queryDoIFollowVloger(@RequestParam String myId,
                                                @RequestParam String vlogerId) {
        return GraceJSONResult.ok(fansService.queryDoIFollowVloger(myId, vlogerId));
    }

    @GetMapping("queryMyFollows")
    public GraceJSONResult queryMyFollows(@RequestParam String myId,
                                          @RequestParam Integer page,
                                          @RequestParam Integer pageSize) {
        return GraceJSONResult.ok(
                fansService.queryMyFollows(
                        myId,
                        page,
                        pageSize));
    }

    @GetMapping("queryMyFans")
    public GraceJSONResult queryMyFans(@RequestParam String myId,
                                          @RequestParam Integer page,
                                          @RequestParam Integer pageSize) {
        return GraceJSONResult.ok(
                fansService.queryMyFans(
                        myId,
                        page,
                        pageSize));
    }
}
