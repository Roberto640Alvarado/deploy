package com.grupo9.blueTicket.models.dtos;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ActiveDTO {
	
	@Email
	private String email;
	private Boolean active;
}
