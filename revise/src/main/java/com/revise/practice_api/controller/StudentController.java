package com.revise.practice_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revise.practice_api.payload.StudentDto;
import com.revise.practice_api.payload.StudentDto2;
import com.revise.practice_api.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/")
	public StudentDto createStud(@RequestBody StudentDto studentDto) {

		StudentDto createdStudent = this.studentService.createStudent(studentDto);
		return createdStudent;
	}

	@PutMapping("/{id}")
	public StudentDto updateStud(@RequestBody StudentDto studentDto, @PathVariable("id") Integer id) {

		System.out.println("Stud Ctrl : " + studentDto);
		System.out.println("Stud Id : " + id);

		StudentDto updatedStudent = this.studentService.updateStudent(studentDto, id);
		return updatedStudent;
	}

	@GetMapping("/{id}")
	public StudentDto updateStud(@PathVariable("id") Integer id) {

		StudentDto updatedStudent = this.studentService.getOneStudent(id);
		return updatedStudent;
	}

//	------------------------------------------------------------------------------------------------------------------------------------
	
	@PutMapping("/u/{id}/s/{sId}")
	public StudentDto2 updateStud2(@RequestBody StudentDto2 studentDto, @PathVariable("id") Integer id,
			@PathVariable("sId") Integer sId) {

		StudentDto2 updatedStudent = this.studentService.updateStudent2(studentDto, id, sId);
		return updatedStudent;
	}
}
