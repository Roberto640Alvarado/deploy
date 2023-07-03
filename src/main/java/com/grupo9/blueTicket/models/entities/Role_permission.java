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
@Table(name = "role_permission")
public class Role_permission {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_permission")
	private Permission id_permission; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role")
	private Role id_role;

	public Role_permission(Permission id_permission, Role id_role) {
		super();
		this.id_permission = id_permission;
		this.id_role = id_role;
	}
	
	
}
