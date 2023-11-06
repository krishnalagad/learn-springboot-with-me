package com.learnspring.graphql.service;

import com.learnspring.graphql.entity.Employee;

import java.util.List;

public interface EmpService {
    Employee create(Employee employee);
    Employee update(Employee employee, Integer id);
    Employee getEmp(Integer id);
    List<Employee> getAll();
    void delete(Integer id);
}