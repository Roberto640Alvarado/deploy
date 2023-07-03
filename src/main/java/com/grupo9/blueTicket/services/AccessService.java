package com.grupo9.blueTicket.services;

import com.grupo9.blueTicket.models.dtos.AccessDTO;

public interface AccessService {
	//Tendrá que verificar si existe un acceso previo en la base de datos, sino existe tendrá que enviar un correo
	void verifyAccess(AccessDTO info) throws Exception;
	
}
