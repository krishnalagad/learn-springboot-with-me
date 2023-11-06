package com.learnspring.graphql.service;

import com.learnspring.graphql.entity.Department;

import java.util.List;

public interface DeptService {
    Department create(Department department);
    Department update(Department department, String id);
    Department getDept(String id);
    List<Department> getAll();
    void delete(String id);
}