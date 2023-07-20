package com.learnspring.rabbitmq.controller;

import com.learnspring.rabbitmq.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private RabbitMQProducer producer;

//    public MessageController(RabbitMQProducer producer) {
//        this.producer = producer;
//    }

    @GetMapping("/publish")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message) {

        this.producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ !!");
    }
}
