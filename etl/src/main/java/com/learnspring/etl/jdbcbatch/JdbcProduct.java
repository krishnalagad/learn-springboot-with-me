package com.learnspring.etl.jdbcbatch;

import com.learnspring.etl.ProductCategory;

import java.time.LocalDate;

public class JdbcProduct {

    private Long productId;
    private String productName;
    private Double productPrice;
    private LocalDate productMnfDate;
    private LocalDate productExpDate;
    private ProductCategory productCategory;

    public JdbcProduct() {
    }

    public JdbcProduct(Long productId, String productName, Double productPrice,
            LocalDate productMnfDate, LocalDate productExpDate, ProductCategory productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productMnfDate = productMnfDate;
        this.productExpDate = productExpDate;
        this.productCategory = productCategory;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public LocalDate getProductMnfDate() {
        return productMnfDate;
    }

    public void setProductMnfDate(LocalDate productMnfDate) {
        this.productMnfDate = productMnfDate;
    }

    public LocalDate getProductExpDate() {
        return productExpDate;
    }

    public void setProductExpDate(LocalDate productExpDate) {
        this.productExpDate = productExpDate;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
