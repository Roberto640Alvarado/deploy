package com.grupo9.blueTicket.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo9.blueTicket.models.entities.Locality;

public interface LocalityRepository extends JpaRepository<Locality, UUID> {

}
