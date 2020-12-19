package com.zuel.item.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
 * 
 * @author:汪思超
 * @app:商品系统redis配置类
 * @date:2020.12.18
 * */

@Configuration
public class RedisCacheConfiguration {

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		org.springframework.data.redis.cache.RedisCacheConfiguration config =
                org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
		config.serializeKeysWith(
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
		
		return RedisCacheManager.builder(
						RedisCacheWriter.nonLockingRedisCacheWriter(factory))
				.cacheDefaults(config)
				.build();
		
		
		
	}
}
