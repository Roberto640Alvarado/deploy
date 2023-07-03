package com.grupo9.blueTicket.services.implementations;

import com.grupo9.blueTicket.models.dtos.SaveTicketDTO;
import com.grupo9.blueTicket.models.dtos.TransferDTO;
import com.grupo9.blueTicket.models.entities.Event;
import com.grupo9.blueTicket.models.entities.Ticket;
import com.grupo9.blueTicket.repositories.TicketRepository;
import com.grupo9.blueTicket.services.EventService;
import com.grupo9.blueTicket.services.TicketService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private EventService eventService;
	
	@Override
	public Ticket findOneById(UUID id) {
		try {
			return ticketRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	@Override
	public void generateQR(String hash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateToken(UUID ticketId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void createTicket(SaveTicketDTO info) {
		Event event = eventService.findOneById(info.getId_event());
		/*Ticket newTicket = new Ticket(
				info.getStatus(),
				event
				);
		
		ticketRepository.save(newTicket);*/
	}

	@Override
	public void transferTicket(TransferDTO info) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String generateTokenContent(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
