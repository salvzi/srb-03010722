package com.atguigu.srb.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.util.RandomUtils;
import com.atguigu.srb.sms.config.SmsProperties;
import com.atguigu.srb.sms.service.SmsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void sendMsgCode(String mobile) {

        // 公共参数配置
        DefaultProfile defaultProfile = DefaultProfile.getProfile(SmsProperties.REGION_Id,SmsProperties.KEY_ID,SmsProperties.KEY_SECRET);

        // 发送短信client
        IAcsClient iAcsClient = new DefaultAcsClient(defaultProfile);

        // 请求参数
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("SendSms");
        commonRequest.setSysVersion("2017-05-25");
        commonRequest.setSysDomain("dysmsapi.aliyuncs.com");
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.putQueryParameter("PhoneNumbers",mobile);
        commonRequest.putQueryParameter("SignName",SmsProperties.SIGN_NAME);
        commonRequest.putQueryParameter("TemplateCode",SmsProperties.TEMPLATE_CODE);
        Map<String,String> paramMap = new HashMap<>();
        String code = RandomUtils.getFourBitRandom();
        paramMap.put("code",code);
        String paramMapJSON = JSON.toJSONString(paramMap);
        commonRequest.putQueryParameter("TemplateParam",paramMapJSON);

        // 发送信息
        CommonResponse commonResponse = null;
        try {
            commonResponse = iAcsClient.getCommonResponse(commonRequest);
            System.out.println("11");
        } catch (ClientException e) {
            e.printStackTrace();
        }

        // 解析请求结果
        HttpResponse httpResponse = commonResponse.getHttpResponse();
        boolean success = httpResponse.isSuccess();
        if(success){
            String data = commonResponse.getData();
            Gson gson = new Gson();
            HashMap<String, String> resultMap = gson.fromJson(data, HashMap.class);
            String OK = resultMap.get("Code");
            Assert.equals(OK,"OK", ResponseEnum.ERROR);
            // 将验证码存入redis
            redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code);
        }
        System.out.println(httpResponse);

    }
}
