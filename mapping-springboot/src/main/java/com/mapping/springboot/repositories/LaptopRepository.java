package com.mapping.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.springboot.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer> {

}
