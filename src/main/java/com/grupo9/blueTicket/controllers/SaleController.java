package com.grupo9.blueTicket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.models.dtos.SaleDTO;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.services.SaleService;
import com.grupo9.blueTicket.services.TicketService;
import com.grupo9.blueTicket.services.UserService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
@CrossOrigin("*")
public class SaleController {
	@Autowired
	private SaleService saleService;
	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;
	
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveSale(@RequestBody @Valid SaleDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		System.out.println(info);
		List<User> allUser = userService.findAll();
		
		for(User user : allUser) {
			System.out.println(user.getEmail());
		}
		
		if (userService.findOneById(info.getId_user()) == null) {
			return new ResponseEntity<>(
                    new MessageDTO("This user does not exists"),
                    HttpStatus.BAD_REQUEST);
		}
		if (ticketService.findOneById(info.getId_ticket()) == null) {
			return new ResponseEntity<>(
                    new MessageDTO("This ticket does not exists"),
                    HttpStatus.BAD_REQUEST);
		}
		try {
			saleService.createSale(info);
			return new ResponseEntity<>(
					new MessageDTO("Ticket created " +info), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}
