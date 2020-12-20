package com.zuel.manage.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zuel.message.provider.TbContentMessagePublisher;

/*
 * 
 * @author:汪思超
 * @app:后台系统消息发送的相关配置
 * @date:2020.12.20
 * */


@Configuration
public class MessageConfiguration {

    @Bean
    public TbContentMessagePublisher contentMessagePublisher(AmqpTemplate template){
        TbContentMessagePublisher contentMessagePublisher =
                new TbContentMessagePublisher();
        contentMessagePublisher.setAmpTemplate(template);
        return contentMessagePublisher;
    }
}
