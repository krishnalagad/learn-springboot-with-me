package com.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	
	@Before("execution(* com.aop.services.impl.PaymentServiceImpl.makePayment(..))")
	public void printBefore() {
		System.out.println("Payment started...");
	}
	
	@After("execution(* com.aop.services.impl.PaymentServiceImpl.makePayment(..))")
	public void printAfter() {
		System.out.println("Payment done...");
	}

}
