package com.revise.custome_finder_methods.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revise.custome_finder_methods.entity.Module;
import com.revise.custome_finder_methods.repositories.ModuleRepository;
import com.revise.custome_finder_methods.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleRepository moduleRepository;

	@Override
	public Module createModule(Module module) {
		Module saved = this.moduleRepository.save(module);
		return saved;
	}

	@Override
	public Module updateModule(Module module) {
		return null;
	}

	@Override
	public Module getOneModule(Integer id) {
		Module module = this.moduleRepository.findById(id).get();
		return module;
	}

	@Override
	public List<Module> getAll() {
		List<Module> allMods = this.moduleRepository.findAll();
		return allMods;
	}

	@Override
	public void deleteModule(Integer id) {
		
	}

}
