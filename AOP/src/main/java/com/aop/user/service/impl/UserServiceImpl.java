package com.aop.user.service.impl;

import com.aop.user.aspects.UserAspect;
import com.aop.user.entity.User;
import com.aop.user.repository.UserRepository;
import com.aop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Override
    public User createUser(User user) {
        User savedUser = this.userRepository.save(user);
        this.user = savedUser;
//        UserAspect ua = new UserAspect(savedUser);
        return savedUser;
    }

    @Override
    public User updateUser(User user, Integer id) {
        User savedUser = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exists !!"));
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(user.getPassword());
        User updatedUser = this.userRepository.save(savedUser);
        return updatedUser;
    }

    @Override
    public User getUser(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exists !!"));
        return user;
    }

    @Override
    public User deleteUser(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exists !!"));
        this.userRepository.delete(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public void setCreateTime() {

    }

    @Override
    public void setUpdateTime() {

    }

    public User createObject() {
        return this.user;
    }
}
