package com.learnspring.graphql.service.impl;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.repository.DeptRepository;
import com.learnspring.graphql.mongorepo.DeptRepositoryMongo;
import com.learnspring.graphql.repository.EmpRepository;
import com.learnspring.graphql.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptRepositoryMongo deptRepositoryMongo;

    @Autowired
    private EmpRepository empRepository;

    private final Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Override
    public Department create(Department department) {
        String strId = UUID.randomUUID().toString();
        department.setDepartmentId(strId);
        Department savedMongo = this.deptRepositoryMongo.save(department);
        this.logger.info("{}", savedMongo);
        return this.deptRepository.save(department);
    }

    @Override
    public Department update(Department department, String id) {
        Department dept = this.deptRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
        dept.setDepartmentName(department.getDepartmentName());
        this.logger.info("control has came to above mongo impl");
        Department updatedDept = this.deptRepositoryMongo.save(dept);
        this.logger.info("control has came to below mongo impl");
        this.logger.info("{}", updatedDept);
        return this.deptRepository.save(dept);
    }

    @Override
    public Department getDept(String id) {
        Department department = this.deptRepositoryMongo.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
        this.logger.info("Get One: {}", department);
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