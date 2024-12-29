package com.learnspring.webservices.restful_web_apis.controller.user;

import com.learnspring.webservices.restful_web_apis.entity.user.User;
import com.learnspring.webservices.restful_web_apis.service.user.UserDaoService;
import org.springframework.web.bind.annotation.*;

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
        return this.userDaoService.findOne(id);
    }

}