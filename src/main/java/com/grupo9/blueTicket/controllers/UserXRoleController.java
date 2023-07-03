package com.grupo9.blueTicket.controllers;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.models.dtos.AssingRoleDTO;
import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.services.UserXRoleService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

@RestController
@RequestMapping("/assing-role")
@CrossOrigin("*")
public class UserXRoleController {
	
	@Autowired
	private UserXRoleService userXRoleService;
	
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/user")
	public ResponseEntity<?> assignRole(@RequestBody AssingRoleDTO info, BindingResult validations) {
	    if (validations.hasErrors()) {
	        return new ResponseEntity<>(
	                errorHandler.mapErrors(validations.getFieldErrors()),
	                HttpStatus.BAD_REQUEST);
	    }
	    
	    try {
	        boolean roleAlreadyAssigned = userXRoleService.checkRoleAlreadyAssigned(info.getId_user(), info.getId_role());
	        if (roleAlreadyAssigned) {
	            return new ResponseEntity<>(
	                    new MessageDTO("El usuario ya tiene asignado ese rol"), HttpStatus.BAD_REQUEST);
	        }

	        userXRoleService.assignRole(info);
	        return new ResponseEntity<>(
	                new MessageDTO("Role assigned"), HttpStatus.CREATED);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(
	                new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> removeRole(@RequestParam UUID id_user, @RequestParam int id_role) {
	    try {
	        userXRoleService.removeRole(id_user, id_role);
	        return new ResponseEntity<>(
	                new MessageDTO("Role removed"), HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(
	                new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


}

