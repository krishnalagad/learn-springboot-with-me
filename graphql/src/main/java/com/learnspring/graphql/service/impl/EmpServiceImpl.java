package com.learnspring.graphql.service.impl;

import com.learnspring.graphql.entity.Employee;
import com.learnspring.graphql.repository.EmpRepository;
import com.learnspring.graphql.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepository;

    @Override
    public Employee create(Employee employee) {
        return null;
    }

    @Override
    public Employee update(Employee employee, Integer id) {
        return null;
    }

    @Override
    public Employee getEmp(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}