package com.grupo9.blueTicket.models.entities;

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
@Table(name = "locality")
public class Locality {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Float price;
	@Column(name = "capacity")
	private int capacity;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event")
    private Event event;
	

	public Locality(String name, Float price, int capacity) {
		super();
		this.name = name;
		this.price = price;
		this.capacity = capacity;
		
	}
	
	
	
	
	
	
}
