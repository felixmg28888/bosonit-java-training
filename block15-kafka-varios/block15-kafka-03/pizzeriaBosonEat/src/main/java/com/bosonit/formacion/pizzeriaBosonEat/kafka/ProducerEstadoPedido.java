package com.bosonit.formacion.pizzeriaBosonEat.kafka;

import com.bosonit.formacion.pizzeriaBosonEat.domain.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerEstadoPedido {

    //Para enviar un mensaje se necesita una instancia de KafkaTemplate
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //La clase objectMapper se necesita para mapear el pedido Json
    ObjectMapper objectMapper = new ObjectMapper();

    public ProducerEstadoPedido(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Con este método le pasamos el mensaje por parámetro
    //que lo enviará al clúster
    //Por parámetro va un pedido que es mapeado con el metodo writeValue...

    public void sendMessage(Pedido pedido) throws JsonProcessingException {
        String pedidoMapeado= objectMapper.writeValueAsString(pedido);

        kafkaTemplate.send("Pedido", pedidoMapeado);
    }
}
