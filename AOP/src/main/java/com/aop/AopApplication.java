package com.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aop.services.PaymentService;

@SpringBootApplication
public class AopApplication implements CommandLineRunner {
	
	@Autowired
	private PaymentService paymentService;

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Application Started...");
		
		this.paymentService.makePayment(23000);
		
	}

}
