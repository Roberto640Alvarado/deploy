package com.grupo9.blueTicket.models.dtos;

import com.grupo9.blueTicket.models.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class TransferDTO {
	
	@NotEmpty
	private User id_user_receptor;
	
	@Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
	@NotEmpty
	private String email;
	
	

}
