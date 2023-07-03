package com.grupo9.blueTicket.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo9.blueTicket.models.entities.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	public User findOneByUsernameOrEmail(String username, String email);
	//Optional<User>findOneByCode(UUID id);
	//Optional<User> findBycode(UUID code);
}
