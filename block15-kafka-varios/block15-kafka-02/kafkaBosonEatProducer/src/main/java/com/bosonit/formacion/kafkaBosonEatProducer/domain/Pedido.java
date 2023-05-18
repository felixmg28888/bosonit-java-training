package com.bosonit.formacion.kafkaBosonEatProducer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido implements Serializable {
    private String pedido;
    private String ingredientes;
    private String estado;


}
