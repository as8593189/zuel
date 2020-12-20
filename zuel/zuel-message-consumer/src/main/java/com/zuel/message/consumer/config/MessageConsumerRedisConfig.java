package com.zuel.message.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.zuel.redis.config.AbstractZuelRedisCacheConfigurer;

/*
 * 
 * @author:汪思超
 * @app:消息消费者的redis配置
 * @date:2020.12.20
 * */

@Configuration
public class MessageConsumerRedisConfig extends  AbstractZuelRedisCacheConfigurer{

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        return super.redisTemplate(redisConnectionFactory);
    }
}
