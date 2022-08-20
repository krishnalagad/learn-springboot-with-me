package com.operation.crudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operation.crudapp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
