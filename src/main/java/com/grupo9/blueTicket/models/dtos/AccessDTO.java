package com.grupo9.blueTicket.models.dtos;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccessDTO {
	@NotEmpty
	//Una implementación del Estandard Official: RFC 5322
	@Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
	private String email;
	@NotEmpty
	//the password must be at least 8 characters, and comply with: 1 uppercase, 1 lowercase, 1 number, 1 character special
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;
	
	@NotEmpty
	private Date session_start_date; 
}
