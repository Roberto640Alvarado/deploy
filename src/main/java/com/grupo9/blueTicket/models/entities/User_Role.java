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
@Table(name = "user_role")
public class User_Role {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
	private User user; //id_user
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
	private Role role; //id_role
	
	@Column(name = "status")
	private Boolean status;
}
