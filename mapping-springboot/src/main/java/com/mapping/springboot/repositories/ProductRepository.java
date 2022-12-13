package com.mapping.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.springboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
