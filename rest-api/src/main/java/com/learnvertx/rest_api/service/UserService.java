package com.learnvertx.rest_api.service;

import com.learnvertx.rest_api.entity.User;
import io.vertx.core.Future;

import java.util.Optional;
import java.util.List;

public interface UserService {

  Future<User> createUser(User user);

  Future<User> updateUser(User user, Integer id);

  Future<Optional<User>> getUser(Integer id);

  Future<List<User>> getUsers();

  Future<Void> deleteUser(Integer id);
}
