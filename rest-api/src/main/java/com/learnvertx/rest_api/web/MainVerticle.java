package com.learnvertx.rest_api.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  private static final Integer PORT = 8080;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);

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
