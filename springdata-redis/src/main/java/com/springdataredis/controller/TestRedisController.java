package com.springdataredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestRedisController {

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@GetMapping("/greetings")
	public ResponseEntity<String> greetings(){
		String cachedStr = (String) redisTemplate.opsForHash().get("customer123", "receiver12");
		redisTemplate.opsForHash().put("customer123", "receiver12", "hello world");
		
		
		return new ResponseEntity<String>(cachedStr, HttpStatus.OK);
	}
}
