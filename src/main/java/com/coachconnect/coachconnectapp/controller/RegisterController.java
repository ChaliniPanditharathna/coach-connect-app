package com.coachconnect.coachconnectapp.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coachconnect.coachconnectapp.model.EnumRole;
import com.coachconnect.coachconnectapp.model.Role;
import com.coachconnect.coachconnectapp.model.RoleRepository;
import com.coachconnect.coachconnectapp.model.User;
import com.coachconnect.coachconnectapp.model.UserRepository;
import com.coachconnect.coachconnectapp.request.RegisterRequest;
import com.coachconnect.coachconnectapp.response.MessageResponse;
import com.coachconnect.coachconnectapp.security.jwt.JwtUtils;
import jakarta.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/coachconnect")
public class RegisterController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
			if (userRepository.existsByUsername(registerRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: The username is already exist!"));
			}

			if (userRepository.existsByEmail(registerRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: The email is already exist!"));
			}

			// Create new user's account
			User user = new User(registerRequest.getUsername(), registerRequest.getEmail(),encoder.encode(registerRequest.getPassword()));

			Set<String> stringRoles = registerRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (stringRoles == null) {
				Role userRole = roleRepository.findByName(EnumRole.ROLE_CLIENT)
						.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
				roles.add(userRole);
			} else {
				stringRoles.forEach(role -> {
					switch (role) {
					case "ROLE_ADMIN":
						Role roleAdmin = roleRepository.findByName(EnumRole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
						roles.add(roleAdmin);
						break;
					case "ROLE_INSTRUCTOR":
						Role roleInstructor = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR)
								.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
						roles.add(roleInstructor);
						break;
					default:
						Role roleClient = roleRepository.findByName(EnumRole.ROLE_CLIENT)
								.orElseThrow(() -> new RuntimeException("Error: The role is not found."));
						roles.add(roleClient);
					}
				});
			}

			user.setRoles(roles);
			userRepository.save(user);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
			//return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}
