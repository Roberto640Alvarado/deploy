package com.grupo9.blueTicket.services;

import com.grupo9.blueTicket.models.dtos.RoleDTO;
import com.grupo9.blueTicket.models.entities.Transfer;

public interface PermissionService {
	void save(RoleDTO info) throws Exception;
	Transfer findOneById(String id);

}
