package com.mapping.springboot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapping.springboot.entity.Laptop;
import com.mapping.springboot.entity.Student;
import com.mapping.springboot.exceptions.ResourceNotFoundException;
import com.mapping.springboot.repositories.StudentRepository;
import com.mapping.springboot.services.LaptopService;
import com.mapping.springboot.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private LaptopService laptopService;

	@Override
	public Student createStudent(Student student) {

		Student savedStudent = this.studentRepository.save(student);
//		Laptop laptop = new Laptop();
//		laptop.setLaptopId(student.getLaptop().getLaptopId());
//		laptop.setModelNumber(student.getLaptop().getModelNumber());
//		laptop.setBrand(student.getLaptop().getBrand());
//		laptop.setStudent(savedStudent);
//		
//		Laptop savedLaptop = laptopService.createLaptop(laptop);
		return savedStudent;
	}

	@Override
	public Student updateStudent(Student student, Integer id) {
		return null;
	}

	@Override
	public Student getStudent(Integer id) {
		Student student = this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "ID", String.valueOf(id)));
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> allStudents = this.studentRepository.findAll();
		return allStudents;
	}

	@Override
	public void deleteStudent(Integer id) {
		Student student = this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "ID", String.valueOf(id)));
		this.studentRepository.delete(student);
	}

}
