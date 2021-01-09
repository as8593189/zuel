package com.zuel.passport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.zuel.redis.config.AbstractZuelRedisCacheConfigurer;

/*
 * @author:汪思超
 * @class:用户登录redis配置
 * @date:2021.1.9
 * */
@Configuration
public class PassportRedisConfiguration extends AbstractZuelRedisCacheConfigurer {

	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        return super.redisTemplate(redisConnectionFactory);
    }
}
