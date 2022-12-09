package com.mapping.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapping.springboot.entity.Laptop;
import com.mapping.springboot.repositories.LaptopRepository;
import com.mapping.springboot.services.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {
	
	@Autowired
	private LaptopRepository laptopRepository;

	@Override
	public Laptop createLaptop(Laptop laptop) {
		Laptop savedLaptop = this.laptopRepository.save(laptop);
		return savedLaptop;
	}

}
