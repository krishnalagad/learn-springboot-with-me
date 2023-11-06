package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Employee;
import com.learnspring.graphql.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee emp = this.empService.create(employee);
        return ResponseEntity.status(201).body(emp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmp(@PathVariable Integer id) {
        Employee emp = this.empService.getEmp(id);
        return ResponseEntity.ok(emp);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> emps = this.empService.getAll();
        return ResponseEntity.ok(emps);
    }
}