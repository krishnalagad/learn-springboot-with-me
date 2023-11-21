package com.learnspring.graphql.service.impl;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.mongorepo.DeptRepositoryMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceMongo {

    @Autowired
    private DeptRepositoryMongo deptRepositoryMongo;

    private final Logger logger = LoggerFactory.getLogger(DeptServiceMongo.class);

    public Department create(Department department) {
        return this.deptRepositoryMongo.save(department);
    }

    public Department update(Department department, String id) {
        Department dept = this.deptRepositoryMongo.findByDepartmentId(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
        dept.setDepartmentName(department.getDepartmentName());
        return this.deptRepositoryMongo.save(dept);
    }

    public Department getOne(String id) {
        return this.deptRepositoryMongo.findByDepartmentId(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
    }

    public List<Department> getAll() {
        return this.deptRepositoryMongo.findAll();
    }

    public void delete(String id) {
        Department department = this.deptRepositoryMongo.findByDepartmentId(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));
        this.deptRepositoryMongo.delete(department);
    }

    //    ------------------------------------------------Testing methoths-------------------------------------------------

    public Department updateDept(Department department, String id) {
        Department dept = this.deptRepositoryMongo.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", id)));

        BeanUtils.copyProperties(department, dept);
        this.logger.info("BeanUtil source: {}", department);
        this.logger.info("BeanUtil target: {}", dept);
        return null;
//        return this.deptRepository.save(dept);
    }
}