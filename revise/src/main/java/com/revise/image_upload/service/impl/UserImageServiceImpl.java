package com.revise.image_upload.service.impl;

import com.revise.image_upload.entity.User;
import com.revise.image_upload.repository.UserRepository;
import com.revise.image_upload.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImageServiceImpl implements UserImageService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        User currentUser = this.userRepository.findById(userId).orElseThrow(() -> new Exception("Requested resource not found !!"));
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        currentUser.setImageName(user.getImageName());

        return this.userRepository.save(currentUser);
    }

    @Override
    public User getUser(Integer id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Requested resource not found !!"));
    }

    @Override
    public void deleteUser(Integer id) throws Exception {
        User user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Requested resource not found !!"));
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
}
