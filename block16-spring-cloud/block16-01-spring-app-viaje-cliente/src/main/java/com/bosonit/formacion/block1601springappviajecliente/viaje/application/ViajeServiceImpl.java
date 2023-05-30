package com.bosonit.formacion.block1601springappviajecliente.viaje.application;


import com.bosonit.formacion.block1601springappviajecliente.cliente.domain.Cliente;
import com.bosonit.formacion.block1601springappviajecliente.cliente.repository.ClienteRepository;
import com.bosonit.formacion.block1601springappviajecliente.exception.EntityNotFoundException;
import com.bosonit.formacion.block1601springappviajecliente.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto.ViajeInputDto;
import com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto.ViajeOutputDto;
import com.bosonit.formacion.block1601springappviajecliente.viaje.domain.Viaje;
import com.bosonit.formacion.block1601springappviajecliente.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.bosonit.formacion.block1601springappviajecliente.cliente.application.ClienteServiceImpl.CLIENTE_NO_ENCONTRADO;

@Service
public class ViajeServiceImpl implements ViajeService {
    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public static final String VIAJE_NO_ENCONTRADO = "Viaje no encontrado.";

    @Override
    public ViajeOutputDto addViaje(ViajeInputDto viajeInputDto) {
        Viaje viaje = new Viaje();
        viaje.setOrigin(viajeInputDto.getOrigin());
        viaje.setDestination(viajeInputDto.getDestination());
        viaje.setDepartureDate(viajeInputDto.getDepartureDate());
        viaje.setArrivalDate(viajeInputDto.getArrivalDate());

        viajeRepository.save(viaje);

        return new ViajeOutputDto(viaje);
    }


    @Override
    public ViajeOutputDto getViajeById(Integer idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));

        return new ViajeOutputDto(viaje);
    }


    @Override
    public List<ViajeOutputDto> getAllViajes() {
        List<Viaje> viajes = viajeRepository.findAll();

        return viajes.stream().map(ViajeOutputDto::new).toList();
    }


    @Override
    public ViajeOutputDto updateViaje(Integer idViaje, ViajeInputDto viajeInputDto) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));

        if (viajeInputDto.getOrigin() != null) viaje.setOrigin(viajeInputDto.getOrigin());
        if (viajeInputDto.getDestination() != null) viaje.setDestination(viajeInputDto.getDestination());
        if (viajeInputDto.getDepartureDate() != null) viaje.setDepartureDate(viajeInputDto.getDepartureDate());
        if (viajeInputDto.getArrivalDate() != null) viaje.setArrivalDate(viajeInputDto.getArrivalDate());


        viajeRepository.save(viaje);

        return new ViajeOutputDto(viaje);
    }


    @Override
    public String deleteViaje(Integer idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));
        viajeRepository.delete(viaje);
        return "El cliente con id " + idViaje + " ha sido borrad@.";
    }


    @Override
    public String addPassenger(Integer idViaje, Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(CLIENTE_NO_ENCONTRADO));

        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));

        if (viaje.getIdViaje() == 40) return "Viaje Completo.";

        List<Cliente> passengers = viaje.getPassengers(); // Obtener la lista de pasajeros existente

        // Verificar si el cliente ya está en la lista de pasajeros
        if (passengers.contains(cliente)) {
            return "El cliente con idCliente " + idCliente + " ya ha reservado este viaje.";
        }

        passengers.add(cliente); // Agregar el nuevo pasajero a la lista existente

        viaje.setPassengers(passengers); // Actualizar la lista de pasajeros en el viaje

        viajeRepository.save(viaje);
        return "Pasajero con idCliente " + idCliente + " añadido al viaje con idViaje " + idViaje+".";
    }


    @Override
    public Integer countPassenger(Integer idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));

        return viaje.getPassengers().size();
    }


    @Override
    public String updateStatus(Integer idViaje, String status) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));

        viaje.setStatus(status);
        viajeRepository.save(viaje);
        return viaje.getStatus();
    }


    @Override
    public String verifyViaje(Integer idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(VIAJE_NO_ENCONTRADO));
        return viaje.getStatus();
    }
}
