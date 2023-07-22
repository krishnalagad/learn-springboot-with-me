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

    @Value("${kafka.topic2.json.name}")
    private String topicTwoJson;

    // Topic 1
    @Bean
    public NewTopic kafkaTopic1() {
        return TopicBuilder.name(topicOne)
                .build();
    }

    @Bean
    public NewTopic kafkaTopic2() {
        return TopicBuilder.name(topicTwoJson)
                .build();
    }
}
