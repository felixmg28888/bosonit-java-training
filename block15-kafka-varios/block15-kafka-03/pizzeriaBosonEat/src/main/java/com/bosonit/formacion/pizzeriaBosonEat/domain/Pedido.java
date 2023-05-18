package com.bosonit.formacion.pizzeriaBosonEat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String pedido;
    private String ingredientes;
    private String estado;

}
