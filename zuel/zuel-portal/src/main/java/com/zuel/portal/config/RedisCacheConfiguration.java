package com.zuel.portal.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import com.zuel.redis.config.AbstractZuelRedisCacheConfigurer;

/*
 * 
 * @author:汪思超
 * @class:前台系统Redis配置
 * @date:2020.12.20
 * */
@Configuration
public class RedisCacheConfiguration extends AbstractZuelRedisCacheConfigurer {

	@Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return super.cacheManager(redisConnectionFactory);
    }
}
