package com.revise.practice_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revise.practice_api.payload.StandardDto;
import com.revise.practice_api.service.StandardService;

@RestController
@RequestMapping("/api/standard")
public class StandardController {
	
	@Autowired
	private StandardService standardService;
	
	@PostMapping("/")
	public StandardDto createStd(@RequestBody StandardDto standardDto) {
		
		StandardDto createdStandard = this.standardService.createStandard(standardDto);
		return createdStandard;
	}
	
	@PutMapping("/{id}")
	public StandardDto createStd(@RequestBody StandardDto standardDto, @PathVariable("id") Integer id) {
		
		System.out.println("Std Ctrl" + standardDto);
		System.out.println("Std Id" + id);
		StandardDto updatedStandard = this.standardService.updateStandard(standardDto, id);
		return updatedStandard;
	}
	
	@GetMapping("/{id}")
	public StandardDto getOne(@PathVariable("id") Integer id) {
		
		StandardDto oneStandard = this.standardService.getOneStandard(id);
		return oneStandard;
	}
	
	@GetMapping("/student/{id}")
	public StandardDto getStandardByStudent(@PathVariable("id") Integer id) {
		
		StandardDto standardDto = this.standardService.getStandardOfStudent(id);
		return standardDto;
	}

}
