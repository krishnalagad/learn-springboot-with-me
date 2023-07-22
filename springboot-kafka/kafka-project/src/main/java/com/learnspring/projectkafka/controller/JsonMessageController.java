package com.learnspring.projectkafka.controller;

import com.learnspring.projectkafka.payload.User;
import com.learnspring.projectkafka.producer.JsonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    @Autowired
    private JsonKafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<?> publish(@RequestBody User user) {
        this.kafkaProducer.sendMessage(user);
        return ResponseEntity.ok("JSON Message sent to kafka topic !!");
    }
}
