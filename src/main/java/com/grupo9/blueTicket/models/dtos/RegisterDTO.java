package com.grupo9.blueTicket.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterDTO {
	@NotEmpty(message = "Username is required")
	private String username;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Password is required")
	private String password;

}
