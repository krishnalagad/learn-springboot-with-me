package com.revise.practice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revise.practice_api.entities.Parent;

public interface ParentRepo extends JpaRepository<Parent, Integer> {

}
