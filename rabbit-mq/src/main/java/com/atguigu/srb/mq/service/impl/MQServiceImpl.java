package com.atguigu.srb.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.srb.mq.pojo.dto.SmsDTO;
import com.atguigu.srb.mq.service.MQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQServiceImpl implements MQService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendCommonMessage(String exchange, String routing, Object o) {
        String json = JSON.toJSONString(o);
        rabbitTemplate.convertAndSend(exchange,routing,o);
    }

    @Override
    public void sendSmsMessage(String exchange, String routing, SmsDTO smsDTO) {
        String json = JSON.toJSONString(smsDTO);
        rabbitTemplate.convertAndSend(exchange,routing,smsDTO);
    }

    @Override
    public void sendTTLMessage(String exchange, String routing, SmsDTO smsDTO) {
        String json = JSON.toJSONString(smsDTO);
        rabbitTemplate.convertAndSend(exchange,routing,smsDTO,processor->{
            processor.getMessageProperties().setExpiration("20000");// 三秒过期时间
            return processor;
        });
    }
}
