package com.grupo9.blueTicket.services.implementations;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.dtos.SaleDTO;
import com.grupo9.blueTicket.models.entities.Sale;
import com.grupo9.blueTicket.models.entities.Ticket;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.repositories.SaleRepository;
import com.grupo9.blueTicket.services.SaleService;
import com.grupo9.blueTicket.services.TicketService;
import com.grupo9.blueTicket.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class SaleServiceImpl implements SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketService ticketService;

	@Override
	public Sale findOneById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sale> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void createSale(SaleDTO info) throws Exception {
		User user = userService.findOneById(info.getId_user());
		Ticket ticket = ticketService.findOneById(info.getId_ticket());
		
		//Convierte la fecha de tipo date a timestamp
		Timestamp datePurchase = new Timestamp(info.getDatePurchase().getTime());
		/*
		Sale newSale = new Sale(
				user,
				datePurchase,
				info.getAmountTicket(),
				info.getTotalAmount(),
				ticket
				);
		*/
		//saleRepository.save(newSale);
		
	}

	@Override
	public void changeActive(Boolean isActive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Search(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
