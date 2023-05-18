package com.bosonit.formacion.block0602personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    @Autowired
    ServicioPersona servicioPersona;

    @Autowired
    Controlador1 controlador1;

    @Autowired
    List<Ciudad> listaCiudades;


    @GetMapping("/getPersona")
    public Persona getPersona(@RequestHeader("nombre") String nombre,
                              @RequestHeader("poblacion") String poblacion,
                              @RequestHeader("edad") int edad) {
        return controlador1.addPersona(nombre,poblacion,edad*2);
    }

    @GetMapping("/getCiudad")
    public List<Ciudad> getCiudad() {
        return listaCiudades;
    }
}