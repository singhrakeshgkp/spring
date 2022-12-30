package com.springdataredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringdataRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdataRedisApplication.class, args);
	}

}
