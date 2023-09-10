package com.revise.pagination_sorting;

import com.revise.pagination_sorting.entity.Product;
import com.revise.pagination_sorting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @PostConstruct
//    public void addDataToDB() {
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(index -> new Product("Product" + index, new Random().nextInt(100),
//                        new Random().nextInt(100000)))
//                .collect(Collectors.toList());
//        this.productRepository.saveAll(products);
//    }

    public List<Product> getProducts() {
        List<Product> all = this.productRepository.findAll();
        return all;
    }
    
    public List<Product> getProductsWithSorting(String fieldName) {
        List<Product> products = this.productRepository.findAll(Sort.by(fieldName));
        return products;
    }

    public List<Product> getProductsWithSortingDesc(String fieldName) {
        List<Product> products = this.productRepository.findAll(Sort.by(Sort.Direction.DESC, fieldName));
        return products;
    }

    public Page<Product> getProductsWithPagination(int offset, int pageSize) {
        Page<Product> page = this.productRepository.findAll(PageRequest.of(offset, pageSize));
        return page;
    }
}
