package com.learnspring.projectkafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic1.name}")
    private String topicOne;

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void sendMessage(String message) {
        this.logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(this.topicOne, message);
    }
}
