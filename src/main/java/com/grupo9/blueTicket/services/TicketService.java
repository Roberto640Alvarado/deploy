package com.grupo9.blueTicket.services;

import java.util.List;
import java.util.UUID;

import com.grupo9.blueTicket.models.dtos.SaveTicketDTO;
import com.grupo9.blueTicket.models.dtos.TransferDTO;
import com.grupo9.blueTicket.models.entities.Ticket;

public interface TicketService {

	Ticket findOneById(UUID id);
	
	List<Ticket> findAll();
	
	void generateQR(String hash);

	void generateToken(UUID ticketId);
	
	void createTicket(SaveTicketDTO info);
	
	void transferTicket(TransferDTO info) throws Exception;
    String generateTokenContent(Ticket ticket);
}
