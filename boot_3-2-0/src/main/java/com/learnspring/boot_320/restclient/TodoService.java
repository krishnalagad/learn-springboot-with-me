package com.learnspring.boot_320.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TodoService {

    @Autowired
    private RestClient restClient;

    public Todo getOneTodo(int id) {
        return this.restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .body(Todo.class);
    }
}
