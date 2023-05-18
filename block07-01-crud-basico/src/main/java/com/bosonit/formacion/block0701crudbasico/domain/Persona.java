package com.bosonit.formacion.block0701crudbasico.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@NoArgsConstructor
//@Getter y @Setter generan un noArgsConstructor en tiempo de compilación
//Con lo que @NoArgsConstructor se puede eliminar
public class Persona {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer edad;
    private String poblacion;
}
