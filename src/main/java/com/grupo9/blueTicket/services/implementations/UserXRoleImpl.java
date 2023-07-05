package com.grupo9.blueTicket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.dtos.AssingRoleDTO;
import com.grupo9.blueTicket.models.entities.Role;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.models.entities.UserXRole;
import com.grupo9.blueTicket.repositories.RoleRepository;
import com.grupo9.blueTicket.repositories.UserRepository;
import com.grupo9.blueTicket.repositories.UserXRoleRepository;
import com.grupo9.blueTicket.services.RoleService;
import com.grupo9.blueTicket.services.UserService;
import com.grupo9.blueTicket.services.UserXRoleService;

import jakarta.transaction.Transactional;

@Service
public class UserXRoleImpl implements UserXRoleService {

	@Autowired
	private UserXRoleRepository userXRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void assignRole(AssingRoleDTO info) throws Exception {
		UserXRole newUserXRole = new UserXRole();
		Role role = roleService.findOneById(info.getId_role());
		User user = userService.findOneById(info.getId_user());
		
		newUserXRole.setRole(role);
		newUserXRole.setUser(user);
		newUserXRole.setStatus(info.getStatus());
		
		userXRoleRepository.save(newUserXRole);
	}

	@Override
	public void defaultRole(UUID userId) {
	    User user = userRepository.findById(userId).orElse(null);
	    Role defaultRole = roleRepository.findById(1).orElse(null);
	    if (user != null && defaultRole != null) {
	        UserXRole newUserRole = new UserXRole(user, defaultRole, true);
	        userXRoleRepository.save(newUserRole);
	    }
	}
	
	public boolean checkRoleAlreadyAssigned(UUID userId, int roleId) {
        UserXRole userXRole = userXRoleRepository.findByUserIdAndRoleId(userId, roleId);
        return userXRole != null;
    }
	

	@Override
	public List<UserXRole> getAll() {
		// TODO Auto-generated method stub
		return userXRoleRepository.findAll();
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void removeRole(UUID userId, int roleId) {
	    User user = userRepository.findById(userId).orElse(null);
	    Role role = roleRepository.findById(roleId).orElse(null);

	    UserXRole userXRole = userXRoleRepository.findByUserAndRole(user, role);
	    if (userXRole != null) {
	        userXRoleRepository.delete(userXRole);
	    }
	}


}
