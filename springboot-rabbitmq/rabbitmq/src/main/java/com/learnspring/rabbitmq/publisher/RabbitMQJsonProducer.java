package com.learnspring.rabbitmq.publisher;

import com.learnspring.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.routing.key.json}")
    private String jsonRoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    public void sendJsonMessage(User user) {
        this.logger.info(String.format("JSON Message sent -> %s", user));

        this.rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }

}
