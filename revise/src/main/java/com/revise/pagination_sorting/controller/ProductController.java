package com.revise.pagination_sorting.controller;

import com.revise.pagination_sorting.ProductService;
import com.revise.pagination_sorting.entity.Product;
import com.revise.pagination_sorting.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ps")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = this.productService.getAll();
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(products.size(), products);
        return ResponseEntity.ok(apiResponse);
    }
}
