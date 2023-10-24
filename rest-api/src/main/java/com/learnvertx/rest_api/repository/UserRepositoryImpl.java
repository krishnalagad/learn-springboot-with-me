package com.learnvertx.rest_api.repository;

import com.learnvertx.rest_api.entity.User;
import io.vertx.core.Future;
import org.hibernate.reactive.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class UserRepositoryImpl implements UserRepository{

  private Stage.SessionFactory sessionFactory;

  public UserRepositoryImpl(Stage.SessionFactory sessionFactory){
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Future<User> createUser(User user) {
    CompletionStage<Void> result = this.sessionFactory.withTransaction((s, t) -> s.persist(user));
    Future<User> future = Future.fromCompletionStage(result).map(v -> user);
    return future;
  }

  @Override
  public Future<User> updateUser(User user, Integer id) {
    return null;
  }

  @Override
  public Future<Optional<User>> getUser(Integer id) {
    return null;
  }

  @Override
  public Future<List<User>> getUsers() {
    return null;
  }

  @Override
  public Future<Void> deleteUser(Integer id) {
    return null;
  }
}
