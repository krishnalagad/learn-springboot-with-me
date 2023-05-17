package com.revise.image_upload.service;

import com.revise.image_upload.entity.User;

import java.util.List;

public interface UserImageService {

    User saveUser(User user);

    User updateUser(User user, Integer userId) throws Exception;

    User getUser(Integer id) throws Exception;

    void deleteUser(Integer id) throws Exception;

    List<User> getUsers();
}
