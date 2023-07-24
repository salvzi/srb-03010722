package com.atguigu.srb.sms.controller;


import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.util.RegexValidateUtils;
import com.atguigu.srb.sms.client.CoreUserInfoClient;
import com.atguigu.srb.sms.service.SmsService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/sms/")
@RestController
public class ApiSmsController {

    @Autowired
    SmsService smsService;

    @Autowired
    CoreUserInfoClient coreUserInfoClient;

    @GetMapping("sendMsgCode/{mobile}")
    public R sendMsgCode(@PathVariable String mobile){
        // 校验手机号
        Assert.notNull(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile),ResponseEnum.MOBILE_ERROR);

        // 远程调用core服务，查询当前手机号是否已经注册
        boolean b = coreUserInfoClient.checkMobileExist(mobile);
        Assert.isTrue(!b,ResponseEnum.MOBILE_EXIST_ERROR);

        smsService.sendMsgCode(mobile);
        return R.ok();
    }
}
