package com.tiket.flight.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiket.flight.publisher.services.TokenAuthenticationService;
import com.tiket.flight.publisher.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class PublisherApplication {
	@Autowired
	private RedisService redisService;

	@Bean
	public TokenAuthenticationService tokenAuthService() {
		return new TokenAuthenticationService(redisService,"abcd");
	}

	@Bean
	public ObjectMapper mapper(){
		return new ObjectMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

}
