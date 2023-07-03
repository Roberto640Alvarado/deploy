package com.grupo9.blueTicket.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.dtos.LocalityDTO;
import com.grupo9.blueTicket.models.entities.Event;
import com.grupo9.blueTicket.models.entities.Locality;
import com.grupo9.blueTicket.repositories.LocalityRepository;
import com.grupo9.blueTicket.services.EventService;
import com.grupo9.blueTicket.services.LocalityService;

import jakarta.transaction.Transactional;

@Service
public class LocalityServiceImpl implements LocalityService{
	
	@Autowired
	private LocalityRepository localityRespository;
	
	@Autowired
	private EventService eventService;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void createLocality(LocalityDTO info) throws Exception {
		Event event = eventService.findOneById(info.getId_event());
		/*
		Locality locality = new Locality(
				info.getName(),
				info.getPrice(),
				info.getCapacity(),
				event
				);
		*/
		//localityRespository.save(locality);
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Locality> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
