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
import com.coachconnect.coachconnectapp.response.RatingCreateResponseDto;
import com.coachconnect.coachconnectapp.request.RatingRequestDto;

@RestController
@RequestMapping("/api")
public class RatingController {
	
	@Autowired
	RatingRepository ratingRepository;

	//@Autowired
	//User userRepository;
	
	/*
	@GetMapping("/ratings")
	public ResponseEntity<List<Rating>> getAllRatings(
				@RequestParam(required = false) Long clientId,
				@RequestParam(required = false) Long instructorId){
				
		
		try {
			List<Rating> ratings = new ArrayList<Rating>();
			
			if(clientId != null) {
				ratingRepository.findByClientId(clientId).forEach(ratings::add);
			}else if(instructorId != null){
				ratingRepository.findByInstructorId(instructorId).forEach(ratings::add);
			}
			
			else {
				ratingRepository.findAll().forEach(ratings::add);
			}
			if(ratings.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(ratings, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
	}
	*/
	
	@PostMapping("/ratings")
	public ResponseEntity<RatingCreateResponseDto> createRating(
										@RequestBody RatingRequestDto ratings){
		
		try {
			Rating _ratings = ratingRepository
								.save(new Rating(ratings.getComment(), ratings.getPoints(),
										ratings.getClient(), ratings.getInstructor()));
			RatingCreateResponseDto ratingResponseDto = new RatingCreateResponseDto();
			ratingResponseDto.setRatings(_ratings);
			ratingResponseDto.setMessage("Rating created successfully");
			return new ResponseEntity<>(ratingResponseDto, HttpStatus.CREATED);		
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
}