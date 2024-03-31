package com.coachconnect.coachconnectapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorRepository;
import com.coachconnect.coachconnectapp.response.InstructorResponseDto;

@RestController
@RequestMapping("/api/coachconnect")

@CrossOrigin(origins = "http://localhost:8081") 
public class InstructorController {
	@Autowired
	InstructorRepository instructorRepository;
	
	@GetMapping("/instructor")
	public ResponseEntity<InstructorResponseDto> getAllInstructors(
			@RequestParam(value = "searchKey", required = false) String searchKey) {
		try {
			
			List<Instructor> instructors = new ArrayList<>();
			InstructorResponseDto instructorResponseDto = new InstructorResponseDto();

			if (searchKey != null && !searchKey.isEmpty()) {
				instructorRepository.findByExpertiseContainingIgnoreCaseOrCityContainingIgnoreCase(searchKey, searchKey)
						.forEach(instructors::add);
			} else {
				instructorRepository.findAll().forEach(instructors::add);
			}

			if (!instructors.isEmpty()) {
				instructorResponseDto.setInstructors(instructors);
				instructorResponseDto.setMessage("Successfully retrieve instructors.");
				instructorResponseDto.setStatus(HttpStatus.OK.name());
			} else {
				instructorResponseDto.setMessage("No instructors.");
				instructorResponseDto.setStatus(HttpStatus.NO_CONTENT.name());
			}

			return new ResponseEntity<>(instructorResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
