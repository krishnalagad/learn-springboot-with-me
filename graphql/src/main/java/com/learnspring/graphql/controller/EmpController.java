package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Employee;
import com.learnspring.graphql.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        Employee emp = this.empService.create(employee);
        return ResponseEntity.status(201).body(emp);
    }
}