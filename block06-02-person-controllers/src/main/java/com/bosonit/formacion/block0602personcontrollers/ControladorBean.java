package com.bosonit.formacion.block0602personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador/bean")
public class ControladorBean {
    @Autowired
    @Qualifier("persona1")
    private Persona persona1;

    @Autowired
    @Qualifier("persona2")
    private Persona persona2;

    @Autowired
    @Qualifier("persona3")
    private Persona persona3;

    @GetMapping("/{bean}")
    public Persona getBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                return persona1;
            case "bean2":
                return persona2;
            case "bean3":
                return persona3;
            default:
                throw new IllegalArgumentException("Bean no encontrado");
        }
    }
}
