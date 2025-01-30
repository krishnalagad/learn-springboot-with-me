package com.learnspring.springcloud.spring_cloud_config_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(SpringCloudConfigServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		logger.info("Spring cloud sever started !!");
	}
}