package com.bosonit.formacion.block1602springappticket.ticket.application;

import com.bosonit.formacion.block1602springappticket.ticket.domain.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="feignViaje", url="${feignViaje.url}")
public interface FeignViaje {
    @GetMapping("/trip/{id}")
    public Viaje getViajeById(@PathVariable Integer id);
}
