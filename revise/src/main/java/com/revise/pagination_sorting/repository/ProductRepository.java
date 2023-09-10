package com.revise.pagination_sorting.repository;

import com.revise.pagination_sorting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
