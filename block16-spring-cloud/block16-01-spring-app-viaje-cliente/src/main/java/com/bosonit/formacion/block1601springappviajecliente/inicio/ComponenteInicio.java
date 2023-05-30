package com.bosonit.formacion.block1601springappviajecliente.inicio;


import com.bosonit.formacion.block1601springappviajecliente.cliente.domain.Cliente;
import com.bosonit.formacion.block1601springappviajecliente.cliente.repository.ClienteRepository;
import com.bosonit.formacion.block1601springappviajecliente.viaje.domain.Viaje;
import com.bosonit.formacion.block1601springappviajecliente.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ComponenteInicio implements CommandLineRunner {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ViajeRepository viajeRepository;


    @Override
    public void run(String... args) throws Exception {

        List<Cliente> clientes = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Cliente cliente = new Cliente();
            cliente.setNombre("Cliente" + i);
            cliente.setApellidos("Cliente" + i +" Cliente"+i);
            cliente.setEdad(18 + i);
            cliente.setEmail("Cliente" + i + "@mail.com");
            cliente.setTelefono("+34 XXX-XX-XX-XX");
            clienteRepository.save(cliente);
            clientes.add(cliente);
        }


        Viaje viaje1 = new Viaje();
        viaje1.setOrigin("Origin1");
        viaje1.setDestination("Destination1");
        viaje1.setDepartureDate("XX-XX-XXXX XXH:XXM");
        viaje1.setArrivalDate("XX-XX-XXXX XXH:XXM");
        viaje1.getPassengers().addAll(clientes.subList(0, 4));
        viajeRepository.save(viaje1);

        Viaje viaje2 = new Viaje();
        viaje2.setOrigin("Origin2");
        viaje2.setDestination("Destination2");
        viaje2.setDepartureDate("XX-XX-XXXX XXH:XXM");
        viaje2.setArrivalDate("XX-XX-XXXX XXH:XXM");
        viaje2.getPassengers().addAll(clientes.subList(4, 8));
        viajeRepository.save(viaje2);


    }
}
