package com.revise.custome_finder_methods.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revise.custome_finder_methods.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
	
	// custom finder method to get modules by module head name.
	List<Module> findByModuleHead(String name);
	
	// custom finder method to get module whose head nane starts with
	List<Module> findByModuleNameStartingWith(String name);

}
