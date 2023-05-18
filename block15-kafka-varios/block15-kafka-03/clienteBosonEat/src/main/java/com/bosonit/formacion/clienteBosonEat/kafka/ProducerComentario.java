package com.bosonit.formacion.clienteBosonEat.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerComentario {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public ProducerComentario(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendComentario(String comentario) {
        kafkaTemplate.send("Comentario", comentario);
    }

}