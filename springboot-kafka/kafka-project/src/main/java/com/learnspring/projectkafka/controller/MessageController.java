package com.learnspring.projectkafka.controller;

import com.learnspring.projectkafka.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    // localhost:8080/api/v1/kafka/publish?message=your message
    @GetMapping("/publish")
    public ResponseEntity<?> publish(@RequestParam("message") String message) {
        this.kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka Topic !!");
    }
}
