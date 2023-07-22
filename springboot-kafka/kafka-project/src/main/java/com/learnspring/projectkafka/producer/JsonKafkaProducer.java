package com.learnspring.projectkafka.producer;

import com.learnspring.projectkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private Logger logger = LoggerFactory.getLogger(JsonKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

//    @Value("${kafka.topic1.name}")
//    private String topicOne;

    @Value("${kafka.topic2.json.name}")
    private String topicTwoJson;

    public void sendMessage(User data) {

        this.logger.info(String.format("Message sent -> %s", data));
        Message<User> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicTwoJson)
                .build();

        this.kafkaTemplate.send(message);
    }
}
