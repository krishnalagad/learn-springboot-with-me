package com.learnvertx.rest_api.service;

import com.learnvertx.rest_api.entity.User;
import com.learnvertx.rest_api.repository.UserRepository;
import io.vertx.core.Future;

public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Future<User> createUser(User user) {
    return this.userRepository.createUser(user);
  }
}
