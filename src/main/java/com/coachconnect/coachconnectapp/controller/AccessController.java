package com.coachconnect.coachconnectapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/access")
public class AccessController {

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/client")
	@PreAuthorize("hasRole('CLIENT') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
	public String clientAccess() {
		return "Client Content.";
	}

	@GetMapping("/instructor")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public String instructorAccess() {
		return "Instructor Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

}
