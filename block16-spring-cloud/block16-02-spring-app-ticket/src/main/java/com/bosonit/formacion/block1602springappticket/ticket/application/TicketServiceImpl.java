package com.bosonit.formacion.block1602springappticket.ticket.application;

import com.bosonit.formacion.block1602springappticket.exception.EntityNotFoundException;
import com.bosonit.formacion.block1602springappticket.ticket.domain.Cliente;
import com.bosonit.formacion.block1602springappticket.ticket.domain.Ticket;
import com.bosonit.formacion.block1602springappticket.ticket.domain.Viaje;
import com.bosonit.formacion.block1602springappticket.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FeignViaje feignViaje;


    @Value("${restTemplate.url}")
    private String restTemplateUrl;

    @Override
    public Ticket creaTicket(Integer idPassenger, Integer idTrip) {
        Cliente cliente = Optional.ofNullable(new RestTemplate()
                        .getForObject(restTemplateUrl + idPassenger, Cliente.class, idPassenger))
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Viaje viaje = feignViaje.getViajeById(idTrip);

//        Viaje viaje = Optional.ofNullable(new RestTemplate().getForObject("http://localhost:8081/trip/{idTrip}",
//                        Viaje.class, idTrip))
//                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado"));

        Ticket ticket = new Ticket();
        ticket.setPassengerId(cliente.getIdCliente());
        ticket.setPassengerName(cliente.getNombre());
        ticket.setPassengerApellidos(cliente.getApellidos());
        ticket.setPassengerEmail(cliente.getEmail());
        ticket.setTripOrigin(viaje.getOrigin());
        ticket.setTripDestination(viaje.getDestination());
        ticket.setTripDepartureDate(viaje.getDepartureDate());
        ticket.setTripArrivalDate(viaje.getArrivalDate());

        ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public Ticket getTicket(Integer idTicket) {
        return ticketRepository.findById(idTicket).orElseThrow(
                () -> new EntityNotFoundException("Ticket no encontrado"));
    }


}
