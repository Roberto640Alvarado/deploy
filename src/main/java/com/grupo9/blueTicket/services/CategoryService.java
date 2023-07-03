package com.grupo9.blueTicket.services;

import java.util.List;

import com.grupo9.blueTicket.models.entities.Category;

public interface CategoryService {

	List<Category> findAll();
	Category findOneById(int id);
}
