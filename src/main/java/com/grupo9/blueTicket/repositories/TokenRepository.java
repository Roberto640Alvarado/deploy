package com.grupo9.blueTicket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo9.blueTicket.models.entities.Token;
import com.grupo9.blueTicket.models.entities.User;

public interface TokenRepository extends JpaRepository<Token, UUID>{
	List<Token> findByUserAndActive(User user, Boolean active);

}
