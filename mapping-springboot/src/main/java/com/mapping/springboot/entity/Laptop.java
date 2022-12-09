package com.mapping.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_laptop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Laptop {
	
	@Id
	private int laptopId;
	private String modelNumber;
	private String brand;
	
	@OneToOne
	private Student student;
}
