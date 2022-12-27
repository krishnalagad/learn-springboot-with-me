package com.revise.custome_finder_methods.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revise.custome_finder_methods.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
	
	List<Module> findByModuleHead(String name);

}
