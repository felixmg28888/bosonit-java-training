package com.bosonit.formacion.kafkaBosonEatConsumer.controller;

import com.bosonit.formacion.kafkaBosonEatConsumer.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/BosonEat/")
public class MessageController {

    @Autowired
    private KafkaConsumer kafkaConsumer;


    public MessageController(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/history")
    public ResponseEntity<List<String>> getHistory(){
        return ResponseEntity.ok(kafkaConsumer.getAllMessages());
    }

    @GetMapping("/last")
    public ResponseEntity<String> getLast(){
        return ResponseEntity.ok(kafkaConsumer.getLastMessage());

    }
}