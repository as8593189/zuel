package com.zuel.item.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/*
 * 
 * @author:汪思超
 * @class:redis配置（为什么直接继承会序列化失败？不解）
 * @date:2020.12.18
 * */

@Configuration
public class RedisCacheConfiguration {
	
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        org.springframework.data.redis.cache.RedisCacheConfiguration config =
                org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
        config
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new StringRedisSerializer()
                        )
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new GenericJackson2JsonRedisSerializer()
                        )
                )
                .entryTtl(Duration.ofMinutes(30L))
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(
                        RedisCacheWriter.nonLockingRedisCacheWriter( 
                                redisConnectionFactory
                        )
                )
                .cacheDefaults(config)
                .build();
    }
}
