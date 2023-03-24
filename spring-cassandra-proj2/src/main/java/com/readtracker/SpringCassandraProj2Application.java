package com.readtracker;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.readtracker.config.DataStaxAstraDbProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraDbProperties.class)
public class SpringCassandraProj2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCassandraProj2Application.class, args);
	}
	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraDbProperties dataStaxAstraDbProperties) {
		Path bundle = dataStaxAstraDbProperties.getSecureConnectBundle().toPath();
		return builder->builder.withCloudSecureConnectBundle(bundle);
	}

}
