package com.mapping.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.springboot.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
