package com.learnvertx.rest_api.web;

import com.learnvertx.rest_api.entity.User;
import com.learnvertx.rest_api.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

  private static final Integer PORT = 8080;
  private final UserService userService;

  public MainVerticle(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    // API to create user
    router.post("/api/v1/user").handler(context -> {
      JsonObject body = context.getBodyAsJson();
      String userName = body.getString("userName");
      String email = body.getString("email");
      String password = body.getString("password");
      User user = new User(null, userName, email, password);

      this.userService.createUser(user)
        .onSuccess(result -> {
          JsonObject object = JsonObject.mapFrom(result);
          context.response().setStatusCode(201).end(object.encodePrettily());
        })
        .onFailure(err -> context.response().setStatusCode(500).end(err.getMessage()));
    });

    // API to update user
    router.put("/api/v1/user/:id").handler(context -> {
      Integer userId = Integer.valueOf(context.pathParam("id"));
      JsonObject body = context.getBodyAsJson();
      User user = new User(body.getInteger("id"), body.getString("userName"), body.getString("email"),
        body.getString("password"));

      this.userService.updateUser(user, userId)
        .onSuccess(result -> {
          System.out.println("Update: " + result);
          context.response().setStatusCode(200).end("Updated successfully.");
        })
        .onFailure(err -> context.response().setStatusCode(500).end(err.getMessage()));
    });

    // API to get user by id
    router.get("/api/v1/user/:id").handler(context -> {
      try {
        Integer userId = Integer.valueOf(context.pathParam("id"));
        this.userService.getUser(userId)
          .onSuccess(result -> {
            if (result.isPresent()) {
              User user = result.get();
              JsonObject obj = new JsonObject();
              obj.put("id", user.getId());
              obj.put("userName", user.getUserName());
              obj.put("email", user.getEmail());
              obj.put("password", user.getPassword());
              context.response().setStatusCode(200).end(obj.encodePrettily());
            } else {
              context.response().setStatusCode(404).end("No Data Found.");
            }
          })
          .onFailure(err -> context.response().setStatusCode(500).end(err.getMessage()));
      } catch (Exception e) {
        context.request().response().setStatusCode(404).end("No Record found with Id");
      }
    });

    vertx.createHttpServer().requestHandler(router).listen(PORT, http -> {
      if (http.succeeded()) {
        System.out.println("HTTP server started on port " + PORT);
        startPromise.complete();
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
