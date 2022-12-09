package com.mapping.springboot.services;

import java.util.List;

import com.mapping.springboot.entity.Student;

public interface StudentService {
	
	Student createStudent(Student student);
	
	Student updateStudent(Student student, Integer id);
	
	Student getStudent(Integer id);
	
	List<Student> getAllStudents();
	
	void deleteStudent(Integer id);
}
