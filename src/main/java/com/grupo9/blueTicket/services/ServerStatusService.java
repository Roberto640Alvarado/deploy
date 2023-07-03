package com.grupo9.blueTicket.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.entities.ServerStatus;
import com.grupo9.blueTicket.repositories.ServerStatusRepository;

@Service
public class ServerStatusService {
    private final ServerStatusRepository serverStatusRepository;

    @Autowired
    public ServerStatusService(ServerStatusRepository serverStatusRepository) {
        this.serverStatusRepository = serverStatusRepository;
    }

    public boolean isServerActive() {
        Optional<ServerStatus> serverStatus = serverStatusRepository.findById(1L);
        return serverStatus.map(ServerStatus::isActive).orElse(false);
    }

    public void setServerActive(boolean active) {
        ServerStatus serverStatus = serverStatusRepository.findById(1L).orElse(new ServerStatus());
        serverStatus.setActive(active);
        serverStatusRepository.save(serverStatus);
    }
}

