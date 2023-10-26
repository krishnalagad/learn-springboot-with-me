package com.learnvertx.rest_api.service;

import com.learnvertx.rest_api.entity.User;
import com.learnvertx.rest_api.repository.UserRepository;
import io.vertx.core.Future;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Future<User> createUser(User user) {
    return this.userRepository.createUser(user);
  }

  @Override
  public Future<User> updateUser(User user, Integer id) {
    return this.userRepository.updateUser(user, id);
  }

  @Override
  public Future<Optional<User>> getUser(Integer id) {
    return this.userRepository.getUser(id);
  }

  @Override
  public Future<List<User>> getUsers() {
    return this.userRepository.getUsers();
  }
}
