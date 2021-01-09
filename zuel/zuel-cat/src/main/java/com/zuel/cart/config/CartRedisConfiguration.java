package com.zuel.cart.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.zuel.redis.config.AbstractZuelRedisCacheConfigurer;

/*
 * @author:汪思超
 * @class:购物车系统缓存配置
 * @date:2021.1.9
 * */
@Configuration
public class CartRedisConfiguration extends AbstractZuelRedisCacheConfigurer {

	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
        return super.redisTemplate(connectionFactory);
    }
	
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return super.cacheManager(redisConnectionFactory);
    }
}
