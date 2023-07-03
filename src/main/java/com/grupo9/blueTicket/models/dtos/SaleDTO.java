package com.grupo9.blueTicket.models.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class SaleDTO {
	
	private UUID id_user;
	
	
	private UUID id_ticket;
	
	
	private Date datePurchase;
	
	private int totalAmount;
	
	private int amountTicket;

}