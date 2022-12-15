package com.revise.custome_validation.entity;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int id;
	
	@NotEmpty
	private String name;
	
	
	private String imageName;
}
