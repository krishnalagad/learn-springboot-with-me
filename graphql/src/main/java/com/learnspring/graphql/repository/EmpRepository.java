package com.learnspring.graphql.repository;

import com.learnspring.graphql.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee, Integer> {
}