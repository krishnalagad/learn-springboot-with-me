package com.learnvertx.rest_api.web;

import com.learnvertx.rest_api.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
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
