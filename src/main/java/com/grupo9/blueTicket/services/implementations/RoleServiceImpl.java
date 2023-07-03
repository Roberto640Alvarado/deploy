package com.grupo9.blueTicket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.entities.Role;
import com.grupo9.blueTicket.repositories.RoleRepository;
import com.grupo9.blueTicket.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findOneById(int id) {
		try {
			return roleRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

}
