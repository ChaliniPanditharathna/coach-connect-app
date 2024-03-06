package com.coachconnect.coachconnectapp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/coachconnect")
public class AdminContoller {
	
	@Autowired
	InstructorRepository instructorRepository;
	
    @PutMapping("/approve-instructor/{instructorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> approveInstructor(@PathVariable Long instructorId) {
        Instructor instructor = instructorRepository.findInstructorById(instructorId);
        if (instructor != null) {
            instructor.setStatus("Active");
            instructor.setUpdatedDate(LocalDateTime.now());
            instructorRepository.save(instructor);
            return ResponseEntity.ok("Instructor approved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor not found");
        }
    }

}
