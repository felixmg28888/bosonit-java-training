package com.bosonit.formacion.block1201criteriabuilder.persona.application;

import com.bosonit.formacion.block1201criteriabuilder.profesor.controller.dto.ProfesorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignProfesor", url = "http://localhost:8080")
public interface FeignProfesor {
    @GetMapping("/profesor/{id}")
    public ProfesorOutputDto getProfesorById(@PathVariable Integer id);

}
