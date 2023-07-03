package com.grupo9.blueTicket.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class SaveRolePermissionDTO {
	
	private UUID id_role;
	
	private UUID id_permission;
}
