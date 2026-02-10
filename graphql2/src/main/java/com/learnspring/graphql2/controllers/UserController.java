package com.learnspring.graphql2.controllers;

import com.learnspring.graphql2.entity.User;
import com.learnspring.graphql2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @MutationMapping(name = "createUser")
    public User createUser(@Argument String name, @Argument String email,
                           @Argument String phone, @Argument String password) {
        User user = new User(name, email, phone, password);
        return userService.createUser(user);
    }

    @QueryMapping(name = "getUser")
    public User getUserById(@Argument Long userId) {
        return userService.getUserById(userId);
    }

    @QueryMapping(name = "getUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User updateUser(Long userId, User user) {
        return userService.updateUser(userId, user);
    }

    @MutationMapping(name = "deleteUser")
    public void deleteUser(@Argument Long userId) {
        userService.deleteUser(userId);
    }
}