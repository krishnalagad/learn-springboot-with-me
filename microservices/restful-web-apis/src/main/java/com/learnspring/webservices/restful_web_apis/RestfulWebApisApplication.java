package com.learnspring.webservices.restful_web_apis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebApisApplication implements CommandLineRunner {

    public static final Logger logger = LoggerFactory.getLogger(RestfulWebApisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebApisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Project Started");
    }
}