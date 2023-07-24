package com.atguigu.srb.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.srb.sms.config.SmsProperties;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class Test1 {

    @Test
    public void b(){
        String regionId = SmsProperties.REGION_ID;
        System.out.println(regionId);
    }

    @Test
    public void a() throws ClientException {

        // 测短信

        // 公共参数配置
        DefaultProfile defaultProfile = DefaultProfile.getProfile("cn-hangzhou","LTAI5tJ5hVrnSsFDrMwwEhJJ","9htMEmLs29ZsWW42VgED64Zv7N07xi");

        // 发送短信client
        IAcsClient iAcsClient = new DefaultAcsClient(defaultProfile);

        // 请求参数
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("SendSms");
        commonRequest.setSysVersion("2017-05-25");
        commonRequest.setSysDomain("dysmsapi.aliyuncs.com");
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.putQueryParameter("PhoneNumbers","18487914373");
        commonRequest.putQueryParameter("SignName","钱鹤强的个人博客");
        commonRequest.putQueryParameter("TemplateCode","SMS_462020486");
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("code","8888");
        String paramMapJSON = JSON.toJSONString(paramMap);
        commonRequest.putQueryParameter("TemplateParam",paramMapJSON);

        // 发送信息
        CommonResponse commonResponse = iAcsClient.getCommonResponse(commonRequest);

        // 解析请求结果
        HttpResponse httpResponse = commonResponse.getHttpResponse();

        System.out.println(httpResponse);


    }
}
