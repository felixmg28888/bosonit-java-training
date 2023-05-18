package com.bosonit.formacion.kafkaBosonEatProducer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
//Bean que crea automáticamente el Topic BosonEat
    @Bean
    public NewTopic kafkaTopic() {
        return TopicBuilder.name("BosonEat").build();
    }
}
