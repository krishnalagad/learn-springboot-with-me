package com.learnspring.springcloud.api_gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Buildable<Route>> routeFunction =
                p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeader", "MyUrl")
                                .addRequestParameter("Param", "Value"))
                        .uri("http://httpbin.org:80");
        return builder.routes()
                .route(routeFunction)   // this is the one way to pass route function
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))  // another way is to implement lambda function
                .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**")   // Redirect to existing rest api
                        .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}