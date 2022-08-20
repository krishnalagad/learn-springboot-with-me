package com.revise.practice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revise.practice_api.entities.Standard;
import com.revise.practice_api.entities.Student;

public interface StandardRepo extends JpaRepository<Standard, Integer> {
	
	Standard findByStudents(Student students);
	
	Standard findByName(String name);
}
