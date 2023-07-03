package com.grupo9.blueTicket.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.models.dtos.RegisterDTO;
import com.grupo9.blueTicket.models.dtos.LoginDTO;
import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.models.dtos.TokenDTO;
import com.grupo9.blueTicket.models.entities.Token;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.services.UserService;
import com.grupo9.blueTicket.services.UserXRoleService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;
import com.grupo9.blueTicket.models.dtos.PasswordDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	
private RequestErrorHandler errorHandler;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserXRoleService userXRoleService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO, BindingResult validations) {
		if (validations.hasErrors()) {
            return new ResponseEntity<>(
                    errorHandler.mapErrors(validations.getFieldErrors()),
                    HttpStatus.BAD_REQUEST);
        }

		User user = userService.findOneByIdentifier(loginDTO.getIdentifier());

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if(!userService.comparePassword(loginDTO.getPassword(),user.getPassword())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	
		try {
			Token token = userService.registerToken(user);
			return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
			} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PostMapping("/signup") /* Implementation Register with Google*/
    public ResponseEntity<?> signUpUser(@RequestBody RegisterDTO registerDTO, BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(
                    errorHandler.mapErrors(validations.getFieldErrors()),
                    HttpStatus.BAD_REQUEST);
        }

        String username = registerDTO.getUsername();
        String email = registerDTO.getEmail();
        String password = registerDTO.getPassword();

        if (userService.findByUsernameOrEmail(username, email) != null) {
            return new ResponseEntity<>(
                    new MessageDTO("Username or email already exists"),
                    HttpStatus.BAD_REQUEST);
        }

        if (password.length()< 8) {
            return new ResponseEntity<>(
                    new MessageDTO("Invalid password: must be at least 8 characters"),
                    HttpStatus.BAD_REQUEST);
        }

        try {
        	User registeredUser = userService.register(registerDTO);
            userXRoleService.defaultRole(registeredUser.getId());
            
            return new ResponseEntity<>(
                    new MessageDTO("User created"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new MessageDTO("Internal Server Error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    
    @PatchMapping("/update/{id}")
	public ResponseEntity<?>updatePassword(@PathVariable(name = "id") UUID id, @RequestBody PasswordDTO info, BindingResult validations){
			User user = userService.findOneById(id);
			
			if(validations.hasErrors()) {
				return new ResponseEntity<>(
						errorHandler.mapErrors(validations.getFieldErrors()), 
						HttpStatus.BAD_REQUEST);
				
			}
		    if(user == null) {
		    	return new ResponseEntity<>(
		    		new MessageDTO("User not found"),
		    		HttpStatus.NOT_FOUND);
		    }
		    try {
				userService.changePassword(id,info);
				return new ResponseEntity<>(
						new MessageDTO("Password update" ), HttpStatus.CREATED);
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(
						new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
    @GetMapping("/whoami")
    public ResponseEntity<User> whoAmI() {
        User user = userService.findUserAuthenticated();
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
