package com.grupo9.blueTicket.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LocalityDTO {
	@NotEmpty
	private String name;
	
	private int capacity;
	
	private Float price;
	private UUID id_event;
}
