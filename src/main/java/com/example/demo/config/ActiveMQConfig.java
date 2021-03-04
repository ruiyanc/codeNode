package com.example.demo.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Component
public class ActiveMQConfig {
    /**
     * 将springboot里面的消息加到jms监听工厂
     *
     * @param connectionFactory
     * @param configurer
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    /**
     * 转换消息格式, 没有这个会爆类型转换错误:
     * Caused by: org.springframework.messaging.converter.MessageConversionException:
     * Cannot convert from [org.apache.activemq.command .ActiveMQTextMessage]
     * to
     * [com.dragon.activemq.demo.springboot.demo.Email]  / [java.lang.String]
     * for org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener$MessagingMessageConverterAdapter$LazyResolutionMessage@3189b1ff
     *
     * @return
     */
    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
