package com.revise.practice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revise.practice_api.entities.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

}
