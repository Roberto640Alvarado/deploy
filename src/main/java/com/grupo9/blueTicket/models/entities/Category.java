package com.grupo9.blueTicket.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = "eventCategory")
@Entity
@Table(name = "category")
public class Category {
    @Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String description;
	
	//mappedBy = "category", 
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Event> eventCategory;
	
}
