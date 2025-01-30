package com.learnspring.springcloud.limits_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimitsServiceApplication implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(LimitsServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LimitsServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		logger.info("LimitsServiceApplication started.");
	}
}