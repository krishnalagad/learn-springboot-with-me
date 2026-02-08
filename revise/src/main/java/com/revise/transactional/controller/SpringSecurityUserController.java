package com.revise.transactional.controller;

import com.revise.transactional.entity.RegisterUserRequest;
import com.revise.transactional.entity.Role;
import com.revise.transactional.entity.UserResponse;
import com.revise.transactional.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class SpringSecurityUserController {

    private final UserService userService;

    public SpringSecurityUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody RegisterUserRequest user) {
        user.setRole(Role.USER);
        UserResponse userResponse = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/create")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody RegisterUserRequest admin) {
        admin.setRole(Role.ADMIN);
        UserResponse userResponse = userService.registerUser(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}