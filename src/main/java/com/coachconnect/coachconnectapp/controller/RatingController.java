package com.coachconnect.coachconnectapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.RatingRepository;
import com.coachconnect.coachconnectapp.model.User;

@RestController
@RequestMapping("/api")
public class RatingController {
	
	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	User userRepository;
	
	
}
