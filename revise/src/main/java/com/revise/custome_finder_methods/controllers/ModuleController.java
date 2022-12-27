package com.revise.custome_finder_methods.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revise.custome_finder_methods.entity.Module;
import com.revise.custome_finder_methods.services.ModuleService;

@RestController
@RequestMapping("/modules")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	@PostMapping("/")
	ResponseEntity<Module> saveModule(Module module){
		Module createModule = this.moduleService.createModule(module);
		return ResponseEntity.status(HttpStatus.CREATED).body(createModule);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Module> getModule(@PathVariable("id") Integer id){
		Module oneModule = this.moduleService.getOneModule(id);
		return ResponseEntity.ok(oneModule);
	}
	
	@GetMapping("/")
	ResponseEntity<List<Module>> getAll(){
		List<Module> all = this.moduleService.getAll();
		return ResponseEntity.ok(all);
	}

}
