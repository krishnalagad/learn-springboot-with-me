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
	
	// get module starting with
	List<Module> getModuleStartsWith(String prefix);
	
	// get module containing string
	List<Module> getModuleContaining(String str);
	
	// get module not containing 
	List<Module> getModuleNotContaining(String str);
	
	// 
	List<Module> getModuleStartsWithString(String str);

}
