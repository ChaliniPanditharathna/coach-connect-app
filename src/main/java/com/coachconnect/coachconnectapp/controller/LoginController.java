package com.coachconnect.coachconnectapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coachconnect.coachconnectapp.model.RoleRepository;
import com.coachconnect.coachconnectapp.model.UserRepository;
import com.coachconnect.coachconnectapp.request.LoginRequest;
import com.coachconnect.coachconnectapp.response.MessageResponse;
import com.coachconnect.coachconnectapp.response.UserInfoResponse;
import com.coachconnect.coachconnectapp.security.jwt.JwtUtils;
import com.coachconnect.coachconnectapp.service.impl.UserDetailsImpl;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/coachconnect")
public class LoginController {

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
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

			Authentication userAuthentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(userAuthentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) userAuthentication.getPrincipal();

			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

			List<String> userRoles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
					new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userRoles));
			
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser() {
			ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
					.body(new MessageResponse("You've been logged out!"));
	}

}
