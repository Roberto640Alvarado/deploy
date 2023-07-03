package com.grupo9.blueTicket.models.entities;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transfer")
public class Transfer{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_issuer")
    private User user_issuer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_receptor")
    private User user_receptor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;
    
    @Column(name = "transfer_date")
    private Timestamp transfer_date;

	public Transfer(UUID id, User user_issuer, User user_receptor, Ticket ticket, Timestamp transfer_date) {
		super();
		this.id = id;
		this.user_issuer = user_issuer;
		this.user_receptor = user_receptor;
		this.ticket = ticket;
		this.transfer_date = transfer_date;
	}

	
    
   
    
}