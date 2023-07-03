package com.grupo9.blueTicket.repositories;

import com.grupo9.blueTicket.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    

}
