package com.bosonit.formacion.block0602personcontrollers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigCiudades {

    @Bean
    public List<Ciudad> CreaListaCiudades() {
        return new ArrayList<>();
    }
}
