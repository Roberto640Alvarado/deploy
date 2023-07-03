package com.grupo9.blueTicket.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class SaveTicketDTO {
	
	private Boolean status;
	private UUID id_event;
}
