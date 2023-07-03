package com.grupo9.blueTicket.repositories;

import com.grupo9.blueTicket.models.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
	public Event findOneByTitle(String title);
}