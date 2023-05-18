package com.bosonit.formacion.pizzeriaBosonEat.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerComentario {
    private String comentario;

    @KafkaListener(topics = "Comentario")
    public void setComentario(String mensaje) {
        comentario = mensaje;
    }

    public String getComentario() {
        return comentario;
    }
}