package com.coachconnect.coachconnectapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Appointment;
import com.coachconnect.coachconnectapp.model.AppointmentRepository;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepo;
	
	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getAllAppointment(
			@RequestParam(required = false)String status){
		try {
			List<Appointment> appointments = new ArrayList<Appointment>();
			if(status == null) {
				appointmentRepo.findAll().forEach(appointments::add);
			}else {
				appointmentRepo.findByStatus(status).forEach(appointments::add);
			}
			if(appointments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(appointments,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Endpoint to approve pending appointments test
    @PutMapping("/appointment/approve")
    public ResponseEntity<String> approvePendingAppointments() {
        try {
            List<Appointment> pendingAppointments = appointmentRepo.findByStatus("PENDING");
            if (pendingAppointments.isEmpty()) {
                return new ResponseEntity<>("No pending appointments found", HttpStatus.NO_CONTENT);
            }
            for (Appointment appointment : pendingAppointments) {
                appointment.setStatus("APPROVED");
            }
            appointmentRepo.saveAll(pendingAppointments);
            return new ResponseEntity<>("All pending appointments approved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to approve pending appointments", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
			
	

}
