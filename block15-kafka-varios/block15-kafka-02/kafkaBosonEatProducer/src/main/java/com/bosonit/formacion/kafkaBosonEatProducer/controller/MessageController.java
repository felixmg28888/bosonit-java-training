package com.bosonit.formacion.kafkaBosonEatProducer.controller;

import com.bosonit.formacion.kafkaBosonEatProducer.domain.Pedido;
import com.bosonit.formacion.kafkaBosonEatProducer.kafka.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/BosonEat/")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;



    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/update")
    public ResponseEntity<String> publish(@RequestBody Pedido pedido) throws JsonProcessingException {
        kafkaProducer.sendMessage(pedido);
        return ResponseEntity.ok("Actualización enviada");
    }

}






