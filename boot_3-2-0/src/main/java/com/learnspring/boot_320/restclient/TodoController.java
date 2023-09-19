package com.learnspring.boot_320.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.todoService.create(todo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getOneTodo(@PathVariable Integer id) {
        return ResponseEntity.ok(this.todoService.getOneTodo(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(this.todoService.getTodos());
    }

}
