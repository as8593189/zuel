package com.zuel.manage.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zuel.message.provider.TbMessagePublisher;

/*
 * 
 * @author:汪思超
 * @app:后台系统消息发送的相关配置
 * @date:2020.12.20
 * */


@Configuration
public class MessageConfiguration {

    @Bean
    public TbMessagePublisher contentMessagePublisher(AmqpTemplate template){
        TbMessagePublisher contentMessagePublisher =
                new TbMessagePublisher();
        contentMessagePublisher.setTemplate(template);
        return contentMessagePublisher;
    }
}
