package com.learnvertx.rest_api.repository;

import com.learnvertx.rest_api.entity.User;
import io.vertx.core.Future;
import jakarta.persistence.criteria.*;
import org.hibernate.reactive.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class UserRepositoryImpl implements UserRepository{

  private final Stage.SessionFactory sessionFactory;

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
    CriteriaBuilder criteriaBuilder = this.sessionFactory.getCriteriaBuilder();
    CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
    Root<User> root = criteriaUpdate.from(User.class);
    Predicate predicate = criteriaBuilder.equal(root.get("id"), user.getId());

    criteriaUpdate.set("userName", user.getUserName());
    criteriaUpdate.set("email", user.getEmail());
    criteriaUpdate.set("password", user.getPassword());
    criteriaUpdate.where(predicate);

    CompletionStage<Integer> result = sessionFactory.withTransaction((s, t) -> s.createQuery(criteriaUpdate)
      .executeUpdate());
    Future<User> future = Future.fromCompletionStage(result).map(r -> user);
    return future;
  }

  @Override
  public Future<Optional<User>> getUser(Integer id) {
    CompletionStage<User> result = this.sessionFactory.withTransaction((s, t) -> s.find(User.class, id));
    Future<Optional<User>> future = Future.fromCompletionStage(result)
      .map(r -> Optional.ofNullable(r))
      .map(r -> r);
    return future;
  }

  @Override
  public Future<List<User>> getUsers() {
    CriteriaBuilder criteriaBuilder = this.sessionFactory.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    CompletionStage<List<User>> result = this.sessionFactory.withTransaction((s, t) -> s.find(User.class));
    Future<List<User>> future = Future.fromCompletionStage(result)
      .map(list -> list);
    return future;
  }

  @Override
  public Future<Void> deleteUser(Integer id) {
    return null;
  }
}
