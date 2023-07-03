package com.grupo9.blueTicket.services;

import java.util.UUID;

import com.grupo9.blueTicket.models.dtos.AssingRoleDTO;

public interface UserXRoleService {
	void assignRole (AssingRoleDTO info) throws Exception; 
	
	void defaultRole (UUID user) throws Exception;
	
	boolean checkRoleAlreadyAssigned(UUID userId, int roleId);
	
	void removeRole(UUID userId, int roleId);
}
