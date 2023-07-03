package com.grupo9.blueTicket.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.grupo9.blueTicket.models.dtos.ActiveEventDTO;
import com.grupo9.blueTicket.models.dtos.SaveEventDTO;
import com.grupo9.blueTicket.models.entities.Event;

public interface EventService {
	
	void createEvent(SaveEventDTO event) throws Exception;
	
	Event findOneById(UUID id);
	Event findOneByTitle(String title);
	
	void updateActiveEvent(UUID id, ActiveEventDTO active) throws Exception;
	void updateCreatedEvent(UUID id, SaveEventDTO event) throws Exception;
	
	//Pagination
	Page<Event> findAll(PageRequest of);
	long count();
	public List<Event> getAll();

    void deleteEvent(UUID eventId);

    List<Event> getAttendedEventsByUserId(UUID userId);

}