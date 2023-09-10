package com.revise.pagination_sorting;

import com.revise.pagination_sorting.entity.Product;
import com.revise.pagination_sorting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void addDataToDB() {
        List<Product> products = IntStream.rangeClosed(1, 200)
                .mapToObj(index -> new Product("Product" + index, new Random().nextInt(100),
                        new Random().nextInt(100000)))
                .collect(Collectors.toList());
        this.productRepository.saveAll(products);
    }

    public List<Product> getAll() {
        List<Product> all = this.productRepository.findAll();
        return all;
    }
}
