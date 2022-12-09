package com.mapping.springboot.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourseName;
	private String fieldName;
	private String fieldValue;
	
	public ResourseNotFoundException() {
		super("Resourse not available !!");
	}

	public ResourseNotFoundException(String resourseName, String fieldName, String fieldValue) {
		super(String.format("%s is not found with %s : %s", resourseName, fieldName, fieldValue));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
