package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Department department) {
        Department dept = this.deptService.create(department);
        return ResponseEntity.status(201).body(dept);
    }
}