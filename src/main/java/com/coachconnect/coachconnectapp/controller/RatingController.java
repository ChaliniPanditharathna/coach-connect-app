package com.coachconnect.coachconnectapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.Rating;
import com.coachconnect.coachconnectapp.model.RatingRepository;
import com.coachconnect.coachconnectapp.model.User;

@RestController
@RequestMapping("/api")
public class RatingController {
	
	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	User userRepository;
	
	@GetMapping("/ratings")
	public ResponseEntity<List<Rating>> getAllRatings(
				@RequestParam(required = false) long id,
				@RequestParam(required = false) Client client,
				@RequestParam(required = false) Instructor instructor){
		
		try {
			List<Rating> ratings = new ArrayList<Rating>();
			
			if(instructor == null) {
				ratingRepository.findAll().forEach(ratings::add);
			}else {
				ratingRepository.findById(id).forEach(ratings::add);
			}
			if(ratings.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(ratings, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
	}
	
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(@RequestBody Rating ratings){
		
		try {
			Rating _ratings = ratingRepository
								.save(new Rating(ratings.getComment(), ratings.getPoints(),
										ratings.getCliendId(), ratings.getInstructorId()));
			return new ResponseEntity<>(_ratings, HttpStatus.CREATED);		
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
}