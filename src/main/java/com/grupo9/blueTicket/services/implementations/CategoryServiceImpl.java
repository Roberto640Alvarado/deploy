package com.grupo9.blueTicket.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.entities.Category;
import com.grupo9.blueTicket.repositories.CategoryRepository;
import com.grupo9.blueTicket.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category findOneById(int id) {
		try {
			return categoryRepository.findById(id)
					.orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}
	

}
