package com.grupo9.blueTicket.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.auth.JWTTools;
import com.grupo9.blueTicket.models.dtos.ActiveDTO;
import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.services.UserService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JWTTools jwtTools;
	
	private RequestErrorHandler errorHandler;
	
	@GetMapping("/all")
	public ResponseEntity<?> userAll(){
		List<User> user = userService.findAll();
		if(user != null) {
			return ResponseEntity.ok(user);	
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PatchMapping("/active")
	public ResponseEntity<?> setActiveUser(@RequestBody @Valid ActiveDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOneByIdentifier(info.getEmail());
		try {
			if (user != null) {
				userService.updateActive(user.getId(), info);
			}else {
				return new ResponseEntity<>(
	                    new MessageDTO("This user does not exists"),
	                    HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(
	                new MessageDTO("The status has change"),
	                HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
		        return new ResponseEntity<>(
		                new MessageDTO("Internal Server Error"),
		                HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(name = "id") UUID id) {
		
	    User user = userService.findOneById(id);
	    
	    if(user == null) {
	    	return new ResponseEntity<>(
	    		new MessageDTO("user not found"),
	    		HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(user, HttpStatus.OK);
	    
	}
	

}
