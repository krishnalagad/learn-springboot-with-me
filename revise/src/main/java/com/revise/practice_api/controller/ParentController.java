package com.revise.practice_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revise.practice_api.payload.ParentDto;
import com.revise.practice_api.service.ParentService;

@RestController
@RequestMapping("/api/parent")
public class ParentController {
	
	@Autowired
	private ParentService parentService;
	
	@PostMapping("/")
	public ParentDto createPar(@RequestBody ParentDto parentDto) {
		
		ParentDto createdParent = this.parentService.createParent(parentDto);
		return createdParent;
	}
	
	@PutMapping("/{id}")
	public ParentDto createPar(@RequestBody ParentDto parentDto, @PathVariable("id") Integer id) {
		
		ParentDto createdParent = this.parentService.updateParent(parentDto, id);
		return createdParent;
	}
}
