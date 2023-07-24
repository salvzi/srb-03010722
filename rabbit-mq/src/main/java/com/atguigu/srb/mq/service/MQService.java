package com.atguigu.srb.mq.service;

import com.atguigu.srb.mq.pojo.dto.SmsDTO;

public interface MQService {
    void sendCommonMessage(String exchange, String routing, Object s2);

    void sendSmsMessage(String exchange, String routing, SmsDTO smsDTO);

    void sendTTLMessage(String exchange, String routing, SmsDTO smsDTO);
}
