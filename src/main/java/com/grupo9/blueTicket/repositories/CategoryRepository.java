package com.grupo9.blueTicket.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo9.blueTicket.models.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
