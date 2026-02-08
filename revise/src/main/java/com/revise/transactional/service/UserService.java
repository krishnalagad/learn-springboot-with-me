package com.revise.transactional.service;

import com.revise.transactional.entity.RegisterUserRequest;
import com.revise.transactional.entity.SpringSecurityUser;
import com.revise.transactional.entity.UserResponse;
import com.revise.transactional.repo.UserDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        // TODO check for existing with the same username
        if (userDetailsRepository.findByUsername(registerUserRequest.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists with an username " + registerUserRequest.getUsername());
        }
        // TODO encrypt the password
        SpringSecurityUser user = new SpringSecurityUser();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setRole(registerUserRequest.getRole());
        // TODO save the user
        SpringSecurityUser savedUser = userDetailsRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }
}