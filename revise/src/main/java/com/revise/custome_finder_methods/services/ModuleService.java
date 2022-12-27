package com.revise.custome_finder_methods.services;

import java.util.ArrayList;
import java.util.List;

import com.revise.custome_finder_methods.entity.Module;

public interface ModuleService {
	
	Module createModule(Module module);
	
	Module updateModule(Module module);
	
	Module getOneModule(Integer id);
	
	List<Module> getAll();
	
	void deleteModule(Integer id);
	
	List<Module> createAllModules(ArrayList<Module> modules);
	
	// get module by module head name.
	List<Module> getModuleByHeadName(String name);

}
