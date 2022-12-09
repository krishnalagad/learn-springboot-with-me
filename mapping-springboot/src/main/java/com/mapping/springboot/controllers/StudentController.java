package com.mapping.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.springboot.entity.Student;
import com.mapping.springboot.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/")
	public ResponseEntity<Student> create(@RequestBody Student student){
		Student createStudent = this.studentService.createStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getOne(@PathVariable("id") Integer id){
		Student student = this.studentService.getStudent(id);
		return ResponseEntity.ok(student);
	}
}
