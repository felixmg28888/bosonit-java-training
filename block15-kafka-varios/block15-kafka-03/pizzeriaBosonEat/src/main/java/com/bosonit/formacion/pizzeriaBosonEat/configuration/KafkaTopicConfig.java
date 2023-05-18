package com.bosonit.formacion.pizzeriaBosonEat.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    //Beans que crean automáticamente los Topics

    @Bean
    public NewTopic pedido() {
        return TopicBuilder.name("Pedido").build();
    }

    @Bean
    public NewTopic feedback() {
        return TopicBuilder.name("Comentario").build();
    }
}
