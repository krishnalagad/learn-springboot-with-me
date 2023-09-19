package com.learnspring.boot_320.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getOneTodo(@PathVariable Integer id) {
        return ResponseEntity.ok(this.todoService.getOneTodo(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(this.todoService.getTodos());
    }

}
