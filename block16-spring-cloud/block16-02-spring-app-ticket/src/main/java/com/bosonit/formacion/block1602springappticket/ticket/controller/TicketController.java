package com.bosonit.formacion.block1602springappticket.ticket.controller;

import com.bosonit.formacion.block1602springappticket.ticket.application.TicketService;
import com.bosonit.formacion.block1602springappticket.ticket.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;


    @PostMapping("/generateTicket/{idPassenger}/{idTrip}")
    public ResponseEntity<Ticket> generateTicket(@PathVariable("idPassenger") Integer idPassenger,
                                             @PathVariable("idTrip") Integer idTrip) {
        return new ResponseEntity<>(ticketService.creaTicket(idPassenger, idTrip), HttpStatus.CREATED);
    }

    @GetMapping("{idTicket}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("idTicket") Integer idTicket) {
        return new ResponseEntity<>(ticketService.getTicket(idTicket), HttpStatus.OK);
    }

}
