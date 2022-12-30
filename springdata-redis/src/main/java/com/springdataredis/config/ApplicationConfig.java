package com.springdataredis.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationConfig {

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("redis", 6379));
	}
	
	@Bean
	public RedisCacheManager redisCacheManager() {
		
        log.info("factory class demo -{}",redisConnectionFactory());
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.prefixCacheNameWith(this.getClass().getPackageName()+".")
				.entryTtl(Duration.ofMinutes(3))
				.disableCachingNullValues();
		return RedisCacheManager.builder(redisConnectionFactory())
								.cacheDefaults(config)
								.build();
		
	}
	
	/*
	 * @Bean public JedisConnectionFactory jedisConnectionFactory() {
	 * RedisStandaloneConfiguration config = new
	 * RedisStandaloneConfiguration("localhost", 6379); return new
	 * JedisConnectionFactory(config); }
	 */   
   @Bean
   public RedisTemplate<?, ?> redisTemplate(){
	   RedisTemplate<?, ?> template = new RedisTemplate<>();
	   template.setConnectionFactory(redisConnectionFactory());
	   
	   return template;
   }
}
