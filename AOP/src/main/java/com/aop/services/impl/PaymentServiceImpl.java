package com.aop.services.impl;

import org.springframework.stereotype.Service;

import com.aop.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void makePayment(int amount) {
		
		System.out.println("Amount RS." + amount + " is ready to transfer.");
		
		System.out.println("Your account is debited with amount RS." + amount + ". Thank you.");
	}

}
