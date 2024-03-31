package com.coachconnect.coachconnectapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.ClientRepository;

@RestController
@RequestMapping("/api/coachconnect")

@CrossOrigin(origins = "http://localhost:8081")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/client/{userId}")
	public ResponseEntity<Client> getAllClients(@PathVariable("userId") long userId) {

		Optional<Client> client = clientRepository.findByUserId(userId);
		if (client.isPresent()) {
			return new ResponseEntity<>(client.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
