package com.revise;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ReviseApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ReviseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Project started...");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
