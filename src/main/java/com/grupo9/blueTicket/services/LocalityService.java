package com.grupo9.blueTicket.services;

import java.util.List;

import com.grupo9.blueTicket.models.dtos.LocalityDTO;
import com.grupo9.blueTicket.models.entities.Locality;

public interface LocalityService {
	void createLocality(LocalityDTO info) throws Exception;
	void delete(String id) throws Exception;
	List<Locality>findAll();
}
