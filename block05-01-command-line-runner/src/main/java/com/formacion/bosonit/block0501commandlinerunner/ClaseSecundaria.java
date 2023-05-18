package com.formacion.bosonit.block0501commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClaseSecundaria implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola desde clase secundaria");
    }
}