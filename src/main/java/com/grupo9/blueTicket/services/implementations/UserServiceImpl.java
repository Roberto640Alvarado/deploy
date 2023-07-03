package com.grupo9.blueTicket.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo9.blueTicket.models.dtos.ActiveDTO;
import com.grupo9.blueTicket.models.dtos.PasswordDTO;
import com.grupo9.blueTicket.models.dtos.RegisterDTO;
import com.grupo9.blueTicket.models.entities.Token;
import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.services.UserService;
import com.grupo9.blueTicket.repositories.TokenRepository;
import com.grupo9.blueTicket.auth.JWTTools;

import jakarta.transaction.Transactional;

import com.grupo9.blueTicket.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	private JWTTools jwtTools;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void updateActive(UUID id, ActiveDTO info) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setActive(info.getActive());
			userRepository.save(user);
		}else {
			throw new Exception("User not found");
		}
	}
	
	public void changePassword(UUID id, PasswordDTO info) throws Exception {
	    User user = findOneById(id);
	    System.out.println(info.getOldPassword());
	    if (user != null) {
	        // Password exists
	        if (comparePassword(info.getOldPassword(), user.getPassword())) {
	            user.setPassword(passwordEncoder.encode(info.getNewPassword()));
	            userRepository.save(user);
	        } else {
	            throw new Exception("Contraseña es distinta");
	        }
	    } else {
	        throw new Exception("User not found");
	    }
	}
	
	@Override
	public Boolean comparePassword(String toCompare, String current) {
		return passwordEncoder.matches(toCompare, current);
	}

	@Override
	public void isActive(Boolean isActive) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findOneByIdentifier(String identifier) {
		return userRepository.findOneByUsernameOrEmail(identifier, identifier);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Token registerToken(User user) throws Exception {
		cleanTokens(user);
		String tokenString = jwtTools.generateToken(user);
		Token token = new Token(tokenString, user);
		tokenRepository.save(token);
		return token;
	}

	@Override
	public Boolean isTokenValid(User user, String token) {
		try {
			cleanTokens(user);
			List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
			tokens.stream().filter(tk -> tk.getContent().equals(token)).findAny().orElseThrow(() -> new Exception());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void cleanTokens(User user) throws Exception {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		tokens.forEach(token -> {
			if (!jwtTools.verifyToken(token.getContent())) {
				token.setActive(false);
				tokenRepository.save(token);
			}
		});
	}

	@Override
	public User findUserAuthenticated() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findOneByUsernameOrEmail(username, username);
	}

	@Override
	public User findByUsernameOrEmail(String username, String email) {
		return userRepository.findOneByUsernameOrEmail(username, email);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	/* Implementation Register with Google*/
	public User register(RegisterDTO info) throws Exception {
		User newUser = new User();

		newUser.setUsername(info.getUsername());
		newUser.setEmail(info.getEmail());
		newUser.setPassword(passwordEncoder.encode(info.getPassword()));

		userRepository.save(newUser);
		
		return newUser;
	}
	
	@Override
	public User findOneById(UUID id) {
		try {
			return userRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
