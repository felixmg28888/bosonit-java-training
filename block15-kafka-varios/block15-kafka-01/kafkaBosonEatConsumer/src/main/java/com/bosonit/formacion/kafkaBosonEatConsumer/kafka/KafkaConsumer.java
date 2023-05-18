package com.bosonit.formacion.kafkaBosonEatConsumer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaConsumer {

    //List history para ir almacenando los estados del pedido
    private List<String> history = new ArrayList<>();


    @KafkaListener(topics = "BosonEat")
    //El KafkaListener va a estar escuchando en el topic en cuestión,
    //cada vez que reciba un mensaje lo pasará por parametro en el método
    //que lo añadirá al list history.
    public void addMessageToHistory(String message) {
        history.add(message);
    }

    public List<String> getAllMessages() {
        return history;
    }

    public String getLastMessage() {
        //Si la list está vacía enviará mensaje
        if (history.isEmpty()) {
            return "Aún no has pedido nada";
        } else {
            return history.get(history.size() - 1);
            //Retornará el último mensaje de la lista.
        }
    }
}
