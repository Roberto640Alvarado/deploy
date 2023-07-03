package com.grupo9.blueTicket.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.dtos.ActiveEventDTO;
import com.grupo9.blueTicket.models.dtos.SaveEventDTO;
import com.grupo9.blueTicket.models.entities.Category;
import com.grupo9.blueTicket.models.entities.Event;
import com.grupo9.blueTicket.repositories.EventRepository;
import com.grupo9.blueTicket.services.CategoryService;
import com.grupo9.blueTicket.services.EventService;

import jakarta.transaction.Transactional;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void createEvent(SaveEventDTO info) throws Exception {
		Category category = categoryService.findOneById(info.getCategory());
		Event newEvent = new Event();
		newEvent.setTitle(info.getTitle());
		newEvent.setDate(info.getDate());
		newEvent.setHour(info.getHour());
		newEvent.setDuration(info.getDuration());
		newEvent.setSponsor(info.getSponsor());
		newEvent.setInvolved(info.getInvolved());
		newEvent.setImage1(info.getImage1());
		newEvent.setImage2(info.getImage2());
		newEvent.setCategory(category);

		eventRepository.save(newEvent);
		
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateActiveEvent(UUID id, ActiveEventDTO active) throws Exception {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			event.setStatus(active.getActive());
			eventRepository.save(event);
		}else {
			throw new Exception("Event not found");
		}
		
		
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateCreatedEvent(UUID id,SaveEventDTO info) throws Exception {
		Category category = categoryService.findOneById(info.getCategory());
		Event newEvent = findOneById(id);
		newEvent.setTitle(info.getTitle());
		newEvent.setDate(info.getDate());
		newEvent.setHour(info.getHour());
		newEvent.setDuration(info.getDuration());
		newEvent.setSponsor(info.getSponsor());
		newEvent.setInvolved(info.getInvolved());
		newEvent.setImage1(info.getImage1());
		newEvent.setImage2(info.getImage2());
		newEvent.setCategory(category);

		eventRepository.save(newEvent);
		
	}

	@Override
	public Event findOneByTitle(String title) {
		return eventRepository.findOneByTitle(title);
	}

	@Override
	public Event findOneById(UUID id) {
		try {
			return eventRepository.findById(id)
					.orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteEvent(UUID eventId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> getAttendedEventsByUserId(UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

	//Controllers Pagination--------------------------------------
	@Override
	public Page<Event> findAll(PageRequest pageRequest) {
		return eventRepository.findAll(pageRequest);
	}

	@Override
	public long count() {
		return eventRepository.count();
	}

	@Override
	public List<Event> getAll() {
		return eventRepository.findAll();
	}
	//------------------------------------------------------------
	
}
