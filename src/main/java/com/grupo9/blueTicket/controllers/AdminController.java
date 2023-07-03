package com.grupo9.blueTicket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.services.ServerStatusService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    private final ServerStatusService serverStatusService;

    @Autowired
    public AdminController(ServerStatusService serverStatusService) {
        this.serverStatusService = serverStatusService;
    }

    @PostMapping("/server/status")
    public ResponseEntity<String> toggleServerStatus(@RequestParam boolean active) {
        serverStatusService.setServerActive(active);
        String message = active ? "El servidor está activado" : "El servidor está desactivado";
        return ResponseEntity.ok(message);
    }
}

