package com.grupo9.blueTicket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo9.blueTicket.models.entities.ServerStatus;

@Repository
public interface ServerStatusRepository extends JpaRepository<ServerStatus, Long> {
    // Métodos personalizados, si los necesitas
}