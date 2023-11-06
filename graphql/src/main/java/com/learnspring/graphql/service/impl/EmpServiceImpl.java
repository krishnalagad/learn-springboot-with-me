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
        return this.empRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee, Integer id) {
        Employee emp = this.empRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Employee of Id: %d is not found", id)));
        emp.setEmail(employee.getEmail());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        return this.empRepository.save(emp);
    }

    @Override
    public Employee getEmp(Integer id) {
        return this.empRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Employee of Id: %d is not found", id)));
    }

    @Override
    public List<Employee> getAll() {
        return this.empRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        Employee emp = this.empRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Employee of Id: %d is not found", id)));
        this.empRepository.delete(emp);
    }
}