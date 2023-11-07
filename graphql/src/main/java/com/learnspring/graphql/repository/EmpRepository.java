package com.learnspring.graphql.repository;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EmpRepository extends JpaRepository<Employee, Integer> {
    Set<Employee> findByDepartment(Department department);
}