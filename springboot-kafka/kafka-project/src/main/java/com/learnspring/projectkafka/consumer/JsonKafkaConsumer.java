package com.learnspring.projectkafka.consumer;

import com.learnspring.projectkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${kafka.topic2.json.name}", groupId = "myGroup")
    public void consume(User user) {
        this.logger.info(String.format("Message received -> %s", user));
    }
}
