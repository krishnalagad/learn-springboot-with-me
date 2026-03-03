package com.learnspring.etl.jpabatch;

import com.learnspring.etl.ProductCategory;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "jpa_products")
public class JpaProduct {

    @Id
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_mnf_date")
    private LocalDate productMnfDate;

    @Column(name = "product_exp_date")
    private LocalDate productExpDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private ProductCategory productCategory;

    public JpaProduct() {
    }

    public JpaProduct(Long productId, String productName, Double productPrice,
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
