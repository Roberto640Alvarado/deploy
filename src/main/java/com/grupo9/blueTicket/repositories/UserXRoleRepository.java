package com.grupo9.blueTicket.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo9.blueTicket.models.entities.UserXRole;

public interface UserXRoleRepository extends JpaRepository<UserXRole, UUID> {
	UserXRole findByUserIdAndRoleId(UUID userId, int roleId);
    boolean existsByUserIdAndRoleId(UUID userId, int roleId);
    
    void deleteByUserIdAndRoleId(UUID userId, int roleId);

}
