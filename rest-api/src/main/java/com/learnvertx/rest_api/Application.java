package com.learnvertx.rest_api;

import com.learnvertx.rest_api.web.MainVerticle;
import io.vertx.core.Vertx;

public class Application {
  public static void main(String[] args) {
    MainVerticle mainVerticle = new MainVerticle();
    Vertx.vertx().deployVerticle(mainVerticle);
  }
}
