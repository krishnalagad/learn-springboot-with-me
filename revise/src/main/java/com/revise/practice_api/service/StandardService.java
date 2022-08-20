package com.revise.practice_api.service;

import java.util.List;

import com.revise.practice_api.payload.StandardDto;

public interface StandardService {

	public StandardDto createStandard(StandardDto standardDto);
	public StandardDto updateStandard(StandardDto standardDto, Integer id);
	public StandardDto getOneStandard(Integer id);
	public List<StandardDto> getAllStandards();
	public void deleteOneStandard(Integer id);
	
	public StandardDto getStandardOfStudent(Integer studId);
	
}
