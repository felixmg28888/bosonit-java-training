package com.bosonit.formacion.block0602personcontrollers;

import org.springframework.stereotype.Service;

@Service
public class ServicioPersona {

    private Persona persona;

    public Persona creaPersona(String nombre, String poblacion, int edad) {
        persona = new Persona(nombre, poblacion, edad);
        return persona;
    }
}
