package com.learnvertx.rest_api;

import com.learnvertx.rest_api.entity.User;
import com.learnvertx.rest_api.repository.UserRepository;
import com.learnvertx.rest_api.repository.UserRepositoryImpl;
import com.learnvertx.rest_api.service.UserService;
import com.learnvertx.rest_api.service.UserServiceImpl;
import com.learnvertx.rest_api.web.MainVerticle;
import io.vertx.core.Vertx;
import org.hibernate.cfg.Configuration;
import org.hibernate.reactive.provider.ReactiveServiceRegistryBuilder;
import org.hibernate.reactive.stage.Stage;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Application {
  public static void main(String[] args) {
    // create hibernate properties
    Properties hibernateProps = new Properties();

    hibernateProps.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/revise?serverTimezone=UTC");
    hibernateProps.put("hibernate.connection.username", "root");
    hibernateProps.put("hibernate.connection.password", "krishna24");
    hibernateProps.put("jakarta.persistence.schema-generation.database.action", "update");
    hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    hibernateProps.put("hibernate.show_sql", true);

    // create hibernate configurations
    Configuration configuration = new Configuration();
    configuration.setProperties(hibernateProps);
    configuration.addAnnotatedClass(User.class);

    // create service registry
    ServiceRegistry serviceRegistry = new ReactiveServiceRegistryBuilder()
      .applySettings(configuration.getProperties())
      .build();

    // create session factory
    Stage.SessionFactory sessionFactory = configuration
      .buildSessionFactory(serviceRegistry)
      .unwrap(Stage.SessionFactory.class);

    // set session factory object into repository constructor.
    UserRepository userRepository = new UserRepositoryImpl(sessionFactory);

    // set repository object into service constructor
    UserService userService = new UserServiceImpl(userRepository);

    Vertx.vertx().deployVerticle(new MainVerticle(userService));
  }
}
