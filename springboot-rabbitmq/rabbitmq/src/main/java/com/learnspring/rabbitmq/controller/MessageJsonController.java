package com.learnspring.rabbitmq.controller;

import com.learnspring.rabbitmq.dto.User;
import com.learnspring.rabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    @Autowired
    private RabbitMQJsonProducer jsonProducer;

    @PostMapping("/publish")
    public ResponseEntity<?> sendJsonMessage(@RequestBody User user) {

        this.jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON message sent to RabbitMQ !!");
    }
}
