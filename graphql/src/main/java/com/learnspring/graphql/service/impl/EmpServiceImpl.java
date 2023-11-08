package com.learnspring.graphql.service.impl;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.entity.Employee;
import com.learnspring.graphql.repository.DeptRepository;
import com.learnspring.graphql.repository.EmpRepository;
import com.learnspring.graphql.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public Employee create(Employee employee) {
        Department department = null;
        if (!employee.getDepartment().getDepartmentId().isBlank()) {
            String departmentId = employee.getDepartment().getDepartmentId();
            department = this.deptRepository.findById(departmentId).orElseThrow(() -> new RuntimeException(
                    String.format("Department is not found with Id: %s", departmentId)));
        }
        Employee save = this.empRepository.save(employee);
        save.setDepartment(department);
        return save;
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

    @Override
    public Set<Employee> findEmployeesByDepartment(String deptId) {
        Department department = this.deptRepository.findById(deptId).orElseThrow(() -> new RuntimeException(
                String.format("Department of Id: %s is not found", deptId)));
        return this.empRepository.findByDepartment(department);
    }
}