package com.learnspring.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigJSON {

    @Value("${rabbitmq.queue1.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.key.json}")
    private String jsonRoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // Spring bean for queue to store JSON data.
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // Spring bean for exchange to store JSON data.
    @Bean
    public TopicExchange jsonExchange() {
        return new TopicExchange(exchange);
    }

    // binding between exchange and queue using routing key for json data.
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue())
                .to(jsonExchange())
                .with(jsonRoutingKey);
    }

    // Spring bean to convert JSON message data
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
