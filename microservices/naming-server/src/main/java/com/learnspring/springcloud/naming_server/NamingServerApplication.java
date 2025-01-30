package com.learnspring.springcloud.naming_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingServerApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(NamingServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Eureka server has started.");
	}
}