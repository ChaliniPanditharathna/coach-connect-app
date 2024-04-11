package com.coachconnect.coachconnectapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Appointment;
import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.ClientRepository;
import com.coachconnect.coachconnectapp.model.EnumStatus;
import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorRepository;
import com.coachconnect.coachconnectapp.model.Rating;
import com.coachconnect.coachconnectapp.model.RatingRepository;
import com.coachconnect.coachconnectapp.request.RatingCreateRequestDto;
import com.coachconnect.coachconnectapp.response.AppointmentCreateResponseDto;
import com.coachconnect.coachconnectapp.util.CoachAppFormat;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/coachconnect")
public class RatingController {

	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	InstructorRepository instructorRepository;

	@PostMapping("/rating")
	public Rating createRating(@RequestBody Rating rating) {

		return ratingRepository.save(rating);
	}

	@PutMapping("/update/rating")
	public ResponseEntity<Rating> updateRating(@RequestBody RatingCreateRequestDto rating) {
		Optional<Client> client = clientRepo.findById(rating.getClientId());
		Optional<Instructor> instructor = instructorRepository.findById(rating.getInstructorId());
		System.out.println("dldldlld" + instructor.get().getCity());
		Rating existingRating = ratingRepository.findByClientIdAndInstructorId(client.get().getId(), rating.getInstructorId());

		// Check if the rating exists
		if (existingRating != null) {
			// Update the fields of the existing rating with the new values
			existingRating.setValue(rating.getValue());

			// Save the updated rating to the database
			 Rating rating1 = ratingRepository.save(existingRating);
			 return new ResponseEntity<>(rating1, HttpStatus.CREATED);
		} else {
			 Rating rating2 =  ratingRepository.save(new Rating(rating.getValue(), rating.getClientId(), instructor.get()));
			// Handle the case where the rating with the given ID is not found
					return new ResponseEntity<>(rating2, HttpStatus.CREATED);
			
		}

	}

	
	// Other endpoints like GET, PUT, DELETE can be added here
}
