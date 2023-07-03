package com.grupo9.blueTicket.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AssingRoleDTO {
	
	private UUID id_user;
	
	private int id_role;
	
	private Boolean status;
}
