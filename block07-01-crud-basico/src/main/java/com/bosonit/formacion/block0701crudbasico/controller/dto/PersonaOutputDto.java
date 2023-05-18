package com.bosonit.formacion.block0701crudbasico.controller.dto;

import com.bosonit.formacion.block0701crudbasico.domain.Persona;
import lombok.Getter;


@Getter
public class PersonaOutputDto {
    private Integer id;
    private String nombre;
    private Integer edad;
    private String poblacion;

    //Constructor con persona por Parámetro.
    public PersonaOutputDto(Persona persona) {
        this.id = persona.getId();
        this.nombre = persona.getName();
        this.edad = persona.getEdad();
        this.poblacion = persona.getPoblacion();
    }
}