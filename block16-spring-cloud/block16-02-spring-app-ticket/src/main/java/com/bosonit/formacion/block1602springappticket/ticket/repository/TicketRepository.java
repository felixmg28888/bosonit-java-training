package com.bosonit.formacion.block1602springappticket.ticket.repository;

import com.bosonit.formacion.block1602springappticket.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
