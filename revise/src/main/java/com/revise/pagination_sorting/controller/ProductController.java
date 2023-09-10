package com.revise.pagination_sorting.controller;

import com.revise.pagination_sorting.ProductService;
import com.revise.pagination_sorting.entity.Product;
import com.revise.pagination_sorting.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ps")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        List<Product> products = this.productService.getProducts();
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(products.size(), products);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/asc/{fieldName}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsWithSorting(@PathVariable String fieldName) {
        List<Product> products = this.productService.getProductsWithSorting(fieldName);
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(products.size(), products);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/desc/{fieldName}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsWithSortingDesc(@PathVariable String fieldName) {
        List<Product> products = this.productService.getProductsWithSortingDesc(fieldName);
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(products.size(), products);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/pagination/{offset}/{size}")
    public ResponseEntity<ApiResponse<Page<Product>>> getProductsWithPagination(@PathVariable Integer offset,
                                                                                @PathVariable Integer size) {
        Page<Product> products = this.productService.getProductsWithPagination(offset, size);
        ApiResponse<Page<Product>> apiResponse = new ApiResponse<>(products.getSize(), products);
        return ResponseEntity.ok(apiResponse);
    }
}
