package com.learnspring.webservices.restful_web_apis.controller.user;

import com.learnspring.webservices.restful_web_apis.entity.user.User;
import com.learnspring.webservices.restful_web_apis.exceptions.UserNotFoundException;
import com.learnspring.webservices.restful_web_apis.service.user.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getOneUser(@PathVariable Integer id) {
        User user = this.userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("Id:" + id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        this.userDaoService.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        User savedUser = this.userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body("User created successfully.");
    }

    @GetMapping("/users/i18n-api")
    public String helloWorldI18N() {
        return "Hello world, welcome to the world of Springboot!!";
    }

}