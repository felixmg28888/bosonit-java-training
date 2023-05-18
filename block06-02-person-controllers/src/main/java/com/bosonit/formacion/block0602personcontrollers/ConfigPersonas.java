package com.bosonit.formacion.block0602personcontrollers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  ConfigPersonas {
    @Bean
    public Persona persona1() {
        return new Persona("bean1", "Ciudad bean1", 20);
    }

    @Bean
    public Persona persona2() {
        return new Persona("bean2", "Ciudad bean2", 30);
    }

    @Bean
    public Persona persona3() {
        return new Persona("bean3", "Ciudad bean3", 40);
    }
}