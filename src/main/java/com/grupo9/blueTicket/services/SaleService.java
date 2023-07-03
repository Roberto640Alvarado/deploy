package com.grupo9.blueTicket.services;

import java.util.List;
import java.util.UUID;

import com.grupo9.blueTicket.models.dtos.SaleDTO;
import com.grupo9.blueTicket.models.entities.Sale;

public interface SaleService {
	
	Sale findOneById(UUID id);
    List<Sale> findAll();
    void createSale(SaleDTO info) throws Exception;
    void changeActive(Boolean isActive);
    void Search(UUID id);

}
