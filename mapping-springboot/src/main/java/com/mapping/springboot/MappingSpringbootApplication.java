package com.mapping.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mapping.springboot.entity.Address;
import com.mapping.springboot.entity.Category;
import com.mapping.springboot.entity.Product;
import com.mapping.springboot.entity.Student;
import com.mapping.springboot.exceptions.ResourceNotFoundException;
import com.mapping.springboot.repositories.CategoryRepository;
import com.mapping.springboot.repositories.ProductRepository;
import com.mapping.springboot.repositories.StudentRepository;

@SpringBootApplication
public class MappingSpringbootApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MappingSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Student student1 = new Student();
//		student1.setStudentName("Krishna");
//		student1.setAbout("About section of krishna.");
//		
//		Address ad1 = new Address();
//		ad1.setStreet("Street1");
//		ad1.setCity("Pune");
//		ad1.setCountry("India");
//		ad1.setStudent(student1);
//		
//		Address ad2 = new Address();
//		ad2.setStreet("Street1");
//		ad2.setCity("Pune");
//		ad2.setCountry("India");
//		ad2.setStudent(student1);
//		
//		student1.getAddressList().addAll(List.of(ad1, ad2));
//		
//		Student savedStudent = this.studentRepository.save(student1);
//		System.out.println(savedStudent);
//		
//		System.out.println(this.studentRepository.findById(102).get());

//		Product product1 = new Product();
//		product1.setPId("pro1");
//		product1.setProductName("Redmi Note 9");
//
//		Product product2 = new Product();
//		product2.setPId("pro2");
//		product2.setProductName("Realme Narzo");
//
//		Product product3 = new Product();
//		product3.setPId("pro3");
//		product3.setProductName("VU SmartTV");
//
//		Category category1 = new Category();
//		category1.setCId("cat1");
//		category1.setTitle("Electronics");
//
//		Category category2 = new Category();
//		category2.setCId("cat2");
//		category2.setTitle("Mobiles");
//
//		List<Product> category1Products = category1.getProducts();
//		category1Products.add(product1);
//		category1Products.add(product2);
//		category1Products.add(product3);
//
//		List<Product> category2Products = category2.getProducts();
//		category2Products.add(product1);
//		category2Products.add(product2);
//
//		this.categoryRepository.save(category1);
//		this.categoryRepository.save(category2);

		Category category = this.categoryRepository.findById("cat2")
				.orElseThrow(() -> new ResourceNotFoundException("Category", "ID", "cat1"));
		System.out.println(category.getProducts().size());

		Product product = this.productRepository.findById("pro1")
				.orElseThrow(() -> new ResourceNotFoundException("Product", "ID", "pro1"));
		System.out.println(product.getCategories().size());

	}

}
