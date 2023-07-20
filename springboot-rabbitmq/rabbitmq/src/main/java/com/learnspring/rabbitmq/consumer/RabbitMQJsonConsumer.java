package com.learnspring.rabbitmq.consumer;

import com.learnspring.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private Logger logger = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue1.name}"})
    public void consumeJsonMessage(User user) {
        this.logger.info(String.format("JSON Message received -> %s", user));
    }
}
