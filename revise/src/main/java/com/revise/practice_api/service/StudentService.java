package com.revise.practice_api.service;

import java.util.List;

import com.revise.practice_api.payload.StudentDto;
import com.revise.practice_api.payload.StudentDto2;

public interface StudentService {

	public StudentDto createStudent(StudentDto studentDto); 
	public StudentDto updateStudent(StudentDto studentDto, Integer id); 
	public StudentDto getOneStudent(Integer id); 
	public List<StudentDto> getAllStudents();
	public void deleteOneStudent(Integer id);
	
//	------------------------------------------------------------------------------------------------------------------------------------
	
	public StudentDto2 createStudent2(StudentDto2 studentDto2);
	public StudentDto2 updateStudent2(StudentDto2 studentDto2, Integer id, Integer stdId); 
}
