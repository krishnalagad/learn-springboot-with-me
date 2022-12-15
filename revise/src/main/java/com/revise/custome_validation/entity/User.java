package com.revise.custome_validation.entity;

import javax.validation.constraints.NotEmpty;

import com.revise.custome_validation.validate.ImageNameValid;

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
	
	@ImageNameValid				// custome bean validation.
	private String imageName;
}
