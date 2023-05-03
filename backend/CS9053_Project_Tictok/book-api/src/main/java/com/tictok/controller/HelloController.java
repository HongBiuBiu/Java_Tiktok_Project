package com.tictok.controller;

import com.tictok.grace.result.GraceJSONResult;
import com.tictok.model.Stu;
import com.tictok.utils.SMSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Hello test api")
@RestController
public class HelloController {

    @ApiOperation(value = "hello")
    @GetMapping("hello")
    public Object hello() {

        Stu stu = new Stu("tictok", 18);
        log.debug(stu.toString());
        log.info(stu.toString());
        log.warn(stu.toString());
        log.error(stu.toString());

        return GraceJSONResult.ok(stu);
//        return GraceJSONResult.errorCustom(ResponseStatusEnum.SYSTEM_ERROR_GLOBAL);
//        return GraceJSONResult.ok("Hello SpringBoot~");
    }

    @Autowired
    private SMSUtils smsUtils;

    @GetMapping("sms")
    public Object sms() throws Exception {

        String code = "123456";
        smsUtils.sendSMS("", code);

        return GraceJSONResult.ok();
    }
}
