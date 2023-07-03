package com.grupo9.blueTicket.services;

import java.util.List;
import java.util.UUID;

import com.grupo9.blueTicket.models.entities.Transfer;

public interface TransferService {
	
	Transfer findOneById(UUID id);
	List<Transfer> findAll();

}
