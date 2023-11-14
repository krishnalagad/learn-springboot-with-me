package com.learnspring.graphql.repository;

import com.learnspring.graphql.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Department, String> {
}