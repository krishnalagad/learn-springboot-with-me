package com.revise.practice_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revise.practice_api.entities.Standard;
import com.revise.practice_api.entities.Student;
import com.revise.practice_api.payload.StudentDto;
import com.revise.practice_api.payload.StudentDto2;
import com.revise.practice_api.repository.StandardRepo;
import com.revise.practice_api.repository.StudentRepo;
import com.revise.practice_api.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StandardRepo standardRepo;

	@Override
	public StudentDto createStudent(StudentDto studentDto) {

		Student student = this.modelMapper.map(studentDto, Student.class);
		Student savedStudent = this.studentRepo.save(student);

		return this.modelMapper.map(savedStudent, StudentDto.class);
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Integer id) {

		Student student = this.studentRepo.findById(id).get();

		if (student != null) {
			student.setName(studentDto.getName());
			student.setMob(studentDto.getMob());
//			student.setStandard(null);

			return this.modelMapper.map(student, StudentDto.class);
		}

		return null;
	}

	@Override
	public StudentDto getOneStudent(Integer id) {

		Student student = this.studentRepo.findById(id).get();
		return this.modelMapper.map(student, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudents() {

		List<Student> allStudents = this.studentRepo.findAll();
		
		return allStudents.stream().map((stud) -> this.modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteOneStudent(Integer id) {
		Student student = this.studentRepo.findById(id).get();
		this.studentRepo.delete(student);
	}
	
//	-----------------------------------------------------------------------------------------------------------------------------------

	@Override
	public StudentDto2 updateStudent2(StudentDto2 studentDto, Integer id, Integer stdId) {
		
		Student student = this.studentRepo.findById(id).get();
		Standard standard = this.standardRepo.findById(stdId).get();
		
		if(student != null) {
			
			student.setName(studentDto.getName());
			student.setMob(studentDto.getMob());
			student.setStandard(standard);
			
			Student savedStudent = this.studentRepo.save(student);
			
			return this.modelMapper.map(savedStudent, StudentDto2.class);
		}
		
		return null;
	}

	@Override
	public StudentDto2 createStudent2(StudentDto2 studentDto2) {
		// TODO Auto-generated method stub
		return null;
	}

}
