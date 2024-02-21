package com.coachconnect.coachconnectapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorRepository;

@RestController
@RequestMapping("/api")
public class InstructorController {
	
	@Autowired
	InstructorRepository instructorRepository;
	
	@GetMapping("/instructor")
	public ResponseEntity<List<Instructor>> getAllInstructors(
			@RequestParam(value = "searchKey", required = false) String searchKey) {
		try {
			List<Instructor> instructors = new ArrayList<>();

			if (searchKey != null && !searchKey.isEmpty()) {
				instructorRepository.findByExpertiseContainingIgnoreCaseOrCityContainingIgnoreCase(searchKey, searchKey)
						.forEach(instructors::add);
			} else {
				instructorRepository.findAll().forEach(instructors::add);
			}

			if (instructors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(instructors, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
