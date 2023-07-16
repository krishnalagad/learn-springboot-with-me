package com.aop.user.controller;

import com.aop.user.entity.User;
import com.aop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = this.userService.createUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("id") Integer id) {
        User updatedUser = this.userService.updateUser(user, id);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = this.userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<LinkedHashSet<User>> getUsers() {
        LinkedHashSet<User> users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Integer id) {
        User deletedUser = this.userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
