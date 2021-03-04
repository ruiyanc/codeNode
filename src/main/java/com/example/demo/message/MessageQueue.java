package com.example.demo.message;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * 队列消息发送者
 */
@Component
public class MessageQueue {
    //返回一个名为my-message的队列,并且注册为bean
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("my-message");
    }

}
