package com.bosonit.formacion.block1602springappticket.ticket.application;

import com.bosonit.formacion.block1602springappticket.ticket.domain.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    Ticket creaTicket(Integer idPassenger, Integer idTrip);

    Ticket getTicket(Integer idTicket);

}
