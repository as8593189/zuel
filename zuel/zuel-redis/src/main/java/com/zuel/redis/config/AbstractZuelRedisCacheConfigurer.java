package com.zuel.redis.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
 * @author:汪思超
 * @class:抽取redis配置的共性
 * @date:2020.12.20
 * */

public abstract class AbstractZuelRedisCacheConfigurer {

	/*
	 * 执行redis操作
	 * */
	protected RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
	
	/*
	 * 正常读写redis操作
	 * 
	 * */
	protected CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig();
        config = config
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
