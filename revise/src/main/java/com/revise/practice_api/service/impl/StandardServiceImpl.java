package com.revise.practice_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revise.practice_api.entities.Standard;
import com.revise.practice_api.entities.Student;
import com.revise.practice_api.payload.StandardDto;
import com.revise.practice_api.repository.StandardRepo;
import com.revise.practice_api.repository.StudentRepo;
import com.revise.practice_api.service.StandardService;

@Service
public class StandardServiceImpl implements StandardService {

	@Autowired
	private StandardRepo standardRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public StandardDto createStandard(StandardDto standardDto) {

		Standard standard = this.modelMapper.map(standardDto, Standard.class);
		Standard savedStandard = this.standardRepo.save(standard);
		return this.modelMapper.map(savedStandard, StandardDto.class);
	}

	@Override
	public StandardDto updateStandard(StandardDto standardDto, Integer id) {

		Standard standard = this.standardRepo.findById(id).get();

		if (standard != null) {
			standard.setName(standardDto.getName());

			return this.modelMapper.map(standard, StandardDto.class);
		}
		return null;
	}

	@Override
	public StandardDto getOneStandard(Integer id) {

		Standard standard = this.standardRepo.findById(id).get();
		return this.modelMapper.map(standard, StandardDto.class);
	}

	@Override
	public List<StandardDto> getAllStandards() {

		List<Standard> allStandards = this.standardRepo.findAll();
		return allStandards.stream().map((standard) -> this.modelMapper.map(standard, StandardDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteOneStandard(Integer id) {

		Standard standard = this.standardRepo.findById(id).get();
		this.standardRepo.delete(standard);
	}

	@Override
	public StandardDto getStandardOfStudent(Integer id) {
		
		Student student = this.studentRepo.findById(id).get();
		Standard standard = this.standardRepo.findByStudents(student);
		
		return this.modelMapper.map(standard, StandardDto.class);
	}

}
