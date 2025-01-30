package com.learnspring.springcloud.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		CurrencyExchangeServiceApplication.logger.info("Currency exchange service started.");
	}
}