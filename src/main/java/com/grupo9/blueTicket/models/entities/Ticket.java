package com.grupo9.blueTicket.models.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = {"transfer","sale"})
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "status")
    private Boolean status;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event", nullable = true)
    private Event event;
    
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Sale> sale; //Conexción con la tabla de venta
   
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    @JsonIgnore 
    private List<Transfer> transfer; //La conexión con Transfer 

	public Ticket(Boolean status, Event event) {
		super();
		this.status = status;
		this.event = event;
	}

	

    
    

}
