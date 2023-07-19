package com.learnspring.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    private Logger logger = LoggerFactory.getLogger(RabbitMqProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    public void sendMessage(String message) {
        logger.info(String.format("Message sent -> %s", message));
        // message bind with exchange and exchange use routingKey to send it to queue.
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);

    }
}
