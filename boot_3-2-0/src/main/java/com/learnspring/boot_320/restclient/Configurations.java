package com.learnspring.boot_320.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Configurations {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/todos";

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
