package com.revise.custome_finder_methods.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	ResponseEntity<Module> saveModule(@RequestBody Module module) {
		Module createModule = this.moduleService.createModule(module);
		return ResponseEntity.status(HttpStatus.CREATED).body(createModule);
	}

	@PostMapping("/all")
	ResponseEntity<List<Module>> saveAll(@RequestBody ArrayList<Module> modules) {
		List<Module> createAllModules = this.moduleService.createAllModules(modules);
		return ResponseEntity.ok(createAllModules);
	}

	@GetMapping("/{id}")
	ResponseEntity<Module> getModule(@PathVariable("id") Integer id) {
		Module oneModule = this.moduleService.getOneModule(id);
		return ResponseEntity.ok(oneModule);
	}

	@GetMapping("/")
	ResponseEntity<List<Module>> getAll() {
		List<Module> all = this.moduleService.getAll();
		return ResponseEntity.ok(all);
	}

//	-------------------------------------------Custome finder method API's-------------------------------------------------------------

	@GetMapping("/head/{name}")
	ResponseEntity<List<Module>> getModulesByName(@PathVariable("name") String name) {
		System.out.println("controller: " + name);
		List<Module> modules = this.moduleService.getModuleByHeadName(name.trim());
		return ResponseEntity.ok(modules);
	}

	@GetMapping("/module-name/{prefix}")
	ResponseEntity<List<Module>> getModulesStartWith(@PathVariable("prefix") String prefix) {
		System.out.println("controller: " + prefix);
		List<Module> modules = this.moduleService.getModuleStartsWith(prefix.trim());
		return ResponseEntity.ok(modules);
	}

	@GetMapping("/search/{str}")
	ResponseEntity<List<Module>> getModuleContaining(@PathVariable("str") String str) {
		List<Module> modules = this.moduleService.getModuleContaining(str);
		return ResponseEntity.ok(modules);
	}
	
	@GetMapping("/not-search/{str}")
	ResponseEntity<List<Module>> getModulesNotContaining(@PathVariable("str") String str){
		List<Module> modules = this.moduleService.getModuleNotContaining(str);
		return ResponseEntity.ok(modules);
	}
}
