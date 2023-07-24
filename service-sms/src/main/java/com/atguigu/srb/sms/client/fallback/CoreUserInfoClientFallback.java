package com.atguigu.srb.sms.client.fallback;


import com.atguigu.srb.sms.client.CoreUserInfoClient;
import org.springframework.stereotype.Component;

@Component
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public boolean checkMobileExist(String mobile) {
        System.out.println("checkMobileExist接口服务调用失败，返回默认兜底结果：true");
        return true;
    }
}
