package com.coachconnect.coachconnectapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Appointment;
import com.coachconnect.coachconnectapp.model.AppointmentRepository;
import com.coachconnect.coachconnectapp.model.EnumStatus;
import com.coachconnect.coachconnectapp.model.InstructorAvailability;
import com.coachconnect.coachconnectapp.model.User;
import com.coachconnect.coachconnectapp.model.UserRepository;
import com.coachconnect.coachconnectapp.security.jwt.JwtUtils;
import com.coachconnect.coachconnectapp.service.impl.UserDetailsImpl;
import com.coachconnect.coachconnectapp.util.CoachAppFormat;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@RestController
@RequestMapping("/api/v1")
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepo;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getAllAppointment(@RequestParam(required = false) Long clientId,
			@RequestParam(required = false) Long instructorId, @RequestParam(required = false) EnumStatus status,
			@RequestParam(required = false) String from, @RequestParam(required = false) String to) {
		try {
			List<Appointment> appointments = new ArrayList<>();
			if (clientId != null) {
				// Find appointments by client id
				appointmentRepo.findByClientId(clientId).forEach(appointments::add);
			} else if (instructorId != null) {
				// Find appointments by instructor id
				appointmentRepo.findByInstructorId(instructorId).forEach(appointments::add);
			} else if (status != null) {
				// Find appointments by status
				appointmentRepo.findByStatus(status).forEach(appointments::add);
			} else {
				// Get all appointments
				appointmentRepo.findAll().forEach(appointments::add);
			}
			if (appointments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Endpoint to approve pending appointments
	@PutMapping("/appointment/approve")
	public ResponseEntity<String> approvePendingAppointments(
			@RequestBody AppointmentApproveRequest appointmentApproveRequest
			) {
				System.out.println(JwtUtils.getCurrentUserId());
		
			
		/*try {
			
			List<Appointment> pendingAppointments = appointmentRepo.findByStatusAndInstructorId(,EnumStatus.STATUS_PENDING);
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
		}*/
	    List<Appointment> collect = appointmentRepo
	    		.findByStatus(EnumStatus.STATUS_PENDING)
	    		.stream()
	    		.filter(a->a.getInstructor().getId().equals(JwtUtils.getCurrentUserId()))
	    		.collect(Collectors.toList());
	    System.out.println(collect.toString());
		return new ResponseEntity<>("Failed to approve pending appointments", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/appointment/create")
	public ResponseEntity<String> createAppointment(
			@RequestBody CreateAppointmentDto createAppointmentDto
			) {
		 LocalDateTime dateTime = CoachAppFormat.stringToLocalDateTime(createAppointmentDto.getDateTime());
		 LocalDateTime createdDateTime = CoachAppFormat.stringToLocalDateTime(createAppointmentDto.getCreatedDate());
		 LocalDateTime updatedDateTime = CoachAppFormat.stringToLocalDateTime(createAppointmentDto.getUpdatedDate());
		 User client = userRepository.findById(createAppointmentDto.getClient())
                 .orElseThrow(() -> new RuntimeException("Client not found"));
		 User instructor = userRepository.findById(createAppointmentDto.getInstructor())
                 .orElseThrow(() -> new RuntimeException("Instructor not found"));
		Appointment appointment = new Appointment(dateTime, createdDateTime,
				 updatedDateTime, EnumStatus.valueOf(createAppointmentDto.getStatus()), client,instructor);
		appointmentRepo.save(appointment);  
         return new ResponseEntity<>("appointment created", HttpStatus.CREATED);
			
	}
	
	

}

class CreateAppointmentDto {
	private String dateTime;
	private String createdDate;
	private String updatedDate;
	private String status;
	private Long client;
	private Long instructor;
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getClient() {
		return client;
	}
	public void setClient(Long client) {
		this.client = client;
	}
	public Long getInstructor() {
		return instructor;
	}
	public void setInstructor(Long instructor) {
		this.instructor = instructor;
	}
	@Override
	public String toString() {
		return "CreateAppointmentDto [dateTime=" + dateTime + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", status=" + status + ", client=" + client + ", instructor=" + instructor + "]";
	}

}

class AppointmentApproveRequest {
	private Long appointmentId;

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
}
