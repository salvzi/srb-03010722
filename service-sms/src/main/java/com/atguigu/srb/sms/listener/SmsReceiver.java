package com.atguigu.srb.sms.listener;

import com.atguigu.srb.mq.pojo.dto.SmsDTO;
import com.atguigu.srb.sms.service.SmsService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SmsReceiver {

    @Autowired
    SmsService smsService;

    @RabbitListener(queues = "queue.dead.1")
    public void b(Channel channel, Message message, SmsDTO smsDTO) throws IOException {
        System.out.println("死信队列测试");
        byte[] body = message.getBody();
        System.out.println(new String(body));// 消息
        System.out.println(smsDTO);// 消息
        System.out.println(1);
        int i = 1/0;
        // 最后一行，相当于return
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "exchange.sms",durable = "true"),
            key={"routing.sms"},
            value=@Queue(value = "queue.sms",durable = "false")
    ))
    public void a(Channel channel, Message message, SmsDTO smsDTO) throws IOException {
        System.out.println("充值成功发短信");
        byte[] body = message.getBody();
        System.out.println(new String(body));// 消息
        System.out.println(smsDTO);// 消息
        System.out.println(1);
        // 最后一行，相当于return
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
