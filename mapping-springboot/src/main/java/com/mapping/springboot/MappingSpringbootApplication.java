package com.mapping.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mapping.springboot.entity.Address;
import com.mapping.springboot.entity.Student;
import com.mapping.springboot.repositories.StudentRepository;

@SpringBootApplication
public class MappingSpringbootApplication implements CommandLineRunner {
	
	@Autowired
	private StudentRepository studentRepository;

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
	}

}
