package com.revise.practice_api.service;

import java.util.List;

import com.revise.practice_api.payload.ParentDto;

public interface ParentService {

	public ParentDto createParent(ParentDto parentDto);
	public ParentDto updateParent(ParentDto parentDto, Integer id);
	public ParentDto getOneParent(Integer id);
	public List<ParentDto> getAllParents();
	public void deleteOneParent(Integer id);
}
