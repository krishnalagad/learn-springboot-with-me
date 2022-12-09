package com.mapping.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mapping.springboot.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponce> handleResourseNotFoundException(ResourseNotFoundException ex){
		String message = ex.getMessage();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponce(message, false));
	}
}
