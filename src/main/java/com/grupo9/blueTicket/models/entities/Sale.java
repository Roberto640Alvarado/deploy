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
@Table(name = "sale")
public class Sale {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    //FK de user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    
    @Column(name = "date_purchase")
    private Timestamp date_purchase;
    
    @Column(name = "amount_tickets_purchased")
    private int amount_ticket;
    
    @Column(name = "total_amount")
    private int total_amount;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

	public Sale(User user, Timestamp date_purchase, int amount_ticket, int total_amount, Ticket ticket) {
		super();
		this.user = user;
		this.date_purchase = date_purchase;
		this.amount_ticket = amount_ticket;
		this.total_amount = total_amount;
		this.ticket = ticket;
	}

	
    
    //@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    //private List<Ticket> ticket; //La conexión con ticket
    
    
    
}