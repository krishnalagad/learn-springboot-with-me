package com.learnspring.interceptors.springboot_interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootInterceptorApplication.class, args);
	}

}