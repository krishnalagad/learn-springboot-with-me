package com.aop.user.service;

import com.aop.user.entity.User;

import java.util.LinkedHashSet;

public interface UserService {

    User createUser(User user);

    User updateUser(User user, Integer id);

    User getUser(Integer id);

    User deleteUser(Integer id);

    LinkedHashSet<User> getUsers();

    void setCreateTime();

    void setUpdateTime();
}
