package com.learnvertx.rest_api.service;

import com.learnvertx.rest_api.entity.User;
import io.vertx.core.Future;

public interface UserService {

  Future<User> createUser(User user);
}
