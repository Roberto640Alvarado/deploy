package com.grupo9.blueTicket.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.models.dtos.AssingRoleDTO;
import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.models.entities.Role;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.models.entities.UserXRole;
import com.grupo9.blueTicket.services.UserService;
import com.grupo9.blueTicket.services.UserXRoleService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

@RestController
@RequestMapping("/assing-role")
@CrossOrigin("*")
public class UserXRoleController {
	
	@Autowired
	private UserXRoleService userXRoleService;
	
	@Autowired
	private UserService userService;
	
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
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> removeRole(@RequestBody AssingRoleDTO info, BindingResult validations) {
	    try {
	        boolean roleAssigned = userXRoleService.checkRoleAlreadyAssigned(info.getId_user(), info.getId_role());
	        if (!roleAssigned) {
	            return new ResponseEntity<>(
	                    new MessageDTO("El usuario no tiene asignado ese rol"), HttpStatus.BAD_REQUEST);
	        }

	        userXRoleService.removeRole(info);
	        return new ResponseEntity<>(
	                new MessageDTO("Role removed"), HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(
	                new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/get-role")
    public ResponseEntity<?> myRole() {
        User user = userService.findUserAuthenticated();
        List<UserXRole> roles = userXRoleService.getAll();
        List<Role> roleAssign = new ArrayList<>();
        for(UserXRole userRole : roles) {
        	if (userRole.getUser().getId().equals(user.getId())) {
				//roleAssign.add(userRole.getRole().toString());
				Role role =  userRole.getRole();
				roleAssign.add(role);
				//return ResponseEntity.ok(userRole.getRole());
			}
        }
        if (user != null) {
            return ResponseEntity.ok(roleAssign);
        } else {
        	return new ResponseEntity<>(
	                new MessageDTO("User not found"), HttpStatus.OK);
        }
    }


}

