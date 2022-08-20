package com.revise.practice_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revise.practice_api.entities.Parent;
import com.revise.practice_api.payload.ParentDto;
import com.revise.practice_api.repository.ParentRepo;
import com.revise.practice_api.service.ParentService;

@Service
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentRepo parentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ParentDto createParent(ParentDto parentDto) {

		Parent parent = this.modelMapper.map(parentDto, Parent.class);
		Parent savedParent = this.parentRepo.save(parent);
		return this.modelMapper.map(savedParent, ParentDto.class);
	}

	@Override
	public ParentDto updateParent(ParentDto parentDto, Integer id) {

		Parent parent = this.parentRepo.findById(id).get();

		if (parent != null) {
			parent.setName(parentDto.getName());
			parent.setMob(parentDto.getMob());

			return this.modelMapper.map(parent, ParentDto.class);
		}
		return null;
	}

	@Override
	public ParentDto getOneParent(Integer id) {

		Parent parent = this.parentRepo.findById(id).get();
		return this.modelMapper.map(parent, ParentDto.class);
	}

	@Override
	public List<ParentDto> getAllParents() {
		List<Parent> allParents = this.parentRepo.findAll();
		return allParents.stream().map((parent) -> this.modelMapper.map(parent, ParentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteOneParent(Integer id) {
		Parent parent = this.parentRepo.findById(id).get();
		this.parentRepo.delete(parent);
	}

}
