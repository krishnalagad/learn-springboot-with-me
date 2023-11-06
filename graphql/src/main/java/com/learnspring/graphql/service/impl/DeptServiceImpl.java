package com.learnspring.graphql.service.impl;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.repository.DeptRepository;
import com.learnspring.graphql.repository.EmpRepository;
import com.learnspring.graphql.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private EmpRepository empRepository;

    @Override
    public Department create(Department department) {
        String strId = UUID.randomUUID().toString();
        department.setDepartmentId(strId);
        return this.deptRepository.save(department);
    }

    @Override
    public Department update(Department department, String id) {
        Department dept = this.deptRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
        dept.setDepartmentName(department.getDepartmentName());
        return this.deptRepository.save(dept);
    }

    @Override
    public Department getDept(String id) {
        return this.deptRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
    }

    @Override
    public List<Department> getAll() {
        return this.deptRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Department dept = this.deptRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
        if (!dept.getEmployees().isEmpty()) {
            this.empRepository.deleteAll(dept.getEmployees());
        }
        this.deptRepository.delete(dept);
    }
}