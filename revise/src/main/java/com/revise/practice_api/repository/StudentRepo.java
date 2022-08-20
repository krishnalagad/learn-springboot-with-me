package com.revise.practice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revise.practice_api.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
