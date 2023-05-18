package com.bosonit.formacion.kafkaBosonEatProducer.controller;

import com.bosonit.formacion.kafkaBosonEatProducer.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/BosonEat/")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;



    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/update")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Actualización enviada");
    }


}






