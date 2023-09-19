package com.learnspring.boot_320.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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

    public List<Todo> getTodos() {
        return this.restClient.get()
                .uri("/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Todo>>() {});
    }

    public Todo create(Todo todo) {
        return this.restClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(todo)
                .retrieve()
                .body(Todo.class);
    }
}
