package com.grupo9.blueTicket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.models.dtos.LocalityDTO;
import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.services.EventService;
import com.grupo9.blueTicket.services.LocalityService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/locality")
@CrossOrigin("*")
public class LocalityController {
	
	@Autowired
	private LocalityService localityService;
	
	@Autowired
	private EventService eventService;
	
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveLocality(@RequestBody @Valid LocalityDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		if (eventService.findOneById(info.getId_event()) == null) {
			return new ResponseEntity<>(
                    new MessageDTO("This event does not exists"),
                    HttpStatus.BAD_REQUEST);
		}
		try {
			localityService.createLocality(info);
			return new ResponseEntity<>(
					new MessageDTO("Locality created " +info), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}
