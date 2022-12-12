package com.mapping.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		
		Student student1 = new Student();
		student1.setStudentName("Krishna");
		student1.setAbout("About section of krishna.");
		
		
	}

}
