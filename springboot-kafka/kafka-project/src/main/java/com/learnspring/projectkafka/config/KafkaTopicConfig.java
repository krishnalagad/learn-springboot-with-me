package com.learnspring.projectkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic1.name}")
    private String topicOne;

    // Topic 1
    @Bean
    public NewTopic kafkaTopic1() {
        return TopicBuilder.name(topicOne)
                .build();
    }
}
