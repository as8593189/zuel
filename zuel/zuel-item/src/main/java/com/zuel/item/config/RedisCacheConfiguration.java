package com.zuel.item.config;


import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import com.zuel.redis.config.AbstractZuelRedisCacheConfigurer;

/*
 * 
 * @author:汪思超
 * @class:redis配置（为什么直接继承会序列化失败？不解）
 * @date:2020.12.18
 * */

@Configuration
public class RedisCacheConfiguration extends AbstractZuelRedisCacheConfigurer {
	
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return super.cacheManager(redisConnectionFactory);
    }
}
