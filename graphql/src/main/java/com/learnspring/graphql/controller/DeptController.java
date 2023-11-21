package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.service.DeptService;
import com.learnspring.graphql.service.impl.DeptServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DeptServiceMongo deptServiceMongo;

    @PostMapping("/")
    public ResponseEntity<Department> create(@RequestBody Department department) {
        Department dept = this.deptService.create(department);
        Department mongoDept = this.deptServiceMongo.create(department);
        System.out.println("Mongo create: " + mongoDept);
        return ResponseEntity.status(201).body(dept);
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAll() {
        List<Department> depts = this.deptService.getAll();
        List<Department> all = this.deptServiceMongo.getAll();
        System.out.println("Mongo get all: " + all);
        return ResponseEntity.ok(depts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDept(@PathVariable String id) {
        Department dept = this.deptService.getDept(id);
        return ResponseEntity.ok(dept);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@RequestBody Department department, @PathVariable String id) {
        Department update = this.deptService.update(department, id);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        this.deptService.delete(id);
        return ResponseEntity.status(204).body(Map.of("response", "Department deleted successfully"));
    }

//    -----------------------------------------------test apis---------------------------------------------------------

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDept(@RequestBody Department department, @PathVariable String id) {
        this.deptServiceMongo.up
        return ResponseEntity.ok();
    }
}