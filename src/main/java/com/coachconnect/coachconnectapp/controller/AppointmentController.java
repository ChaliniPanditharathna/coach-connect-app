package com.coachconnect.coachconnectapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorAvailability;
import com.coachconnect.coachconnectapp.model.InstructorRepository;
import com.coachconnect.coachconnectapp.model.User;
import com.coachconnect.coachconnectapp.model.UserRepository;
import com.coachconnect.coachconnectapp.request.AppointmentApproveRequest;
import com.coachconnect.coachconnectapp.request.AppointmentRejectRequest;
import com.coachconnect.coachconnectapp.request.AppointmentRequestDto;
import com.coachconnect.coachconnectapp.request.AppointmentRescheduleRequest;
import com.coachconnect.coachconnectapp.response.AppointmentCreateResponseDto;
import com.coachconnect.coachconnectapp.response.AppointmentDto;
import com.coachconnect.coachconnectapp.response.AppointmentHistroyDto;
import com.coachconnect.coachconnectapp.security.jwt.JwtUtils;
import com.coachconnect.coachconnectapp.sendemail.EmailService;
import com.coachconnect.coachconnectapp.service.impl.UserDetailsImpl;
import com.coachconnect.coachconnectapp.util.CoachAppFormat;


@RestController
@RequestMapping("/api/coachconnect")
@CrossOrigin(origins = "http://localhost:8081")
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepo;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	InstructorRepository instructorRepo;
	
	

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

	// End point to approve pending appointments
	@PutMapping("/appointment/approve")
	public ResponseEntity<Appointment> approvePendingAppointments(
			@RequestBody AppointmentApproveRequest appointmentApproveRequest) {
		Long currentUserId = JwtUtils.getCurrentUserId();
		List<Appointment> pendingAppointments = appointmentRepo.findByStatus(EnumStatus.STATUS_PENDING);

		for (Appointment appointment : pendingAppointments) {
			Long instructorUserId = appointment.getInstructor().getUserId();
			if (instructorUserId.equals(currentUserId)
					&& appointment.getId().equals(appointmentApproveRequest.getAppointmentId())) {
				System.out.println("Instructor " + instructorUserId);
				appointment.setStatus(EnumStatus.STATUS_APPROVED);
				appointment.setUpdatedDate(CoachAppFormat.getCurrentLocalDateTime());
				appointmentRepo.save(appointment);
				String subject = "Appointment Approved";
				String body = "Your appointment has been approved.";
				sendEmailNotification(appointment, subject, body);
				return new ResponseEntity<>(appointment, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/appointment")
	public ResponseEntity<AppointmentCreateResponseDto> createAppointment(
			@RequestBody AppointmentRequestDto appointment) {

		try {
	
			Appointment _appointment = appointmentRepo.save(new Appointment(LocalDateTime.now(),LocalDateTime.now() , EnumStatus.valueOf(appointment.getStatus().toString()),
							 appointment.getDate(), appointment.getInstructorAvailability(),appointment.getClient(), appointment.getInstructor(), null ));

			AppointmentCreateResponseDto appointmentResponseDto = new AppointmentCreateResponseDto();
			appointmentResponseDto.setAppointment(_appointment);
			appointmentResponseDto.setMessage("Sucsessfully created appointment.");
			appointmentResponseDto.setStatus(HttpStatus.CREATED.name());

			return new ResponseEntity<>(appointmentResponseDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//End point for reject appointments
		@PutMapping("/appointment/reject")
		public ResponseEntity<Appointment> rejectPendingAppointments(
				@RequestBody AppointmentRejectRequest appointmentRejectRequest){
			List<Appointment> pendingAppointments = appointmentRepo.findByStatus(EnumStatus.STATUS_PENDING);
			Long currentUserId = JwtUtils.getCurrentUserId();
			
			for (Appointment appointment : pendingAppointments) {
				Long instructorUserId = appointment.getInstructor().getUserId();
		        if (instructorUserId.equals(currentUserId)
		                && appointment.getId().equals(appointmentRejectRequest.getAppointmentId())) {
		        	
		            appointment.setStatus(EnumStatus.STATUS_REJECTED);
		            appointment.setRejectedReason(appointmentRejectRequest.getRejectedReason());
		            appointment.setUpdatedDate(CoachAppFormat.getCurrentLocalDateTime());
		            appointmentRepo.save(appointment);
		            
		            String subject = "Appointment has been rejected";
					String body = "Your appointment has been rejected.";
					sendEmailNotification(appointment, subject, body);
		            return new ResponseEntity<>(appointment, HttpStatus.OK);
		        }
		    }
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	
		//Endpoint for reschedule appointment
		@PutMapping("/appointment/reschedule")
		public ResponseEntity<Appointment> reschedulePendingAppointments(
		        @RequestBody AppointmentRescheduleRequest appointmentRescheduleRequest) {
		    Long currentUserId = JwtUtils.getCurrentUserId();
		    List<Appointment> pendingAppointments = appointmentRepo.findByStatus(EnumStatus.STATUS_PENDING);
		    
		    for (Appointment appointment : pendingAppointments) {
		    	Long instructorUserId = appointment.getInstructor().getUserId();
		        if (instructorUserId.equals(currentUserId)
		                && appointment.getId().equals(appointmentRescheduleRequest.getAppointmentId())) {
		        	
		            appointment.setStatus(EnumStatus.STATUS_APPROVED);
		            appointment.setDate(appointmentRescheduleRequest.getNewDate());
		            appointment.setUpdatedDate(CoachAppFormat.getCurrentLocalDateTime());
		            appointmentRepo.save(appointment);
		            String subject = "Appointment has been rescheduled";
					String body = "Your appointment has been rescheduled.";
					sendEmailNotification(appointment, subject, body);
		            return new ResponseEntity<>(appointment, HttpStatus.OK);
		        }
		    }
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	
	@GetMapping("/appointment/history")
	public ResponseEntity<AppointmentHistroyDto> getAppoinmentByCurrentDateAndInstructorId(
			@RequestParam(value = "searchKey", required = true) String searchKey) {

		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();
		AppointmentHistroyDto appointmentResponseDto = new AppointmentHistroyDto();
		try {
			long currentUserId = JwtUtils.getCurrentUserId(); 
		
			Instructor instructor = instructorRepo.findByUserId(currentUserId);
			
			if ("previous".equals(searchKey)) {
				appointmentRepo.findByDateBeforeAndInstructorId(LocalDate.now(), instructor.getId()).forEach(appointments::add);

			}
			if ("upcomming".equals(searchKey)) {
				appointmentRepo.findByDateAfter(LocalDate.now()).forEach(appointments::add);
			}

			AppointmentDto appointmentDto = new AppointmentDto();
			for (Appointment appointment : appointments) {
			
				appointmentDto.setDate(appointment.getDate());
				appointmentDto.setClient(appointment.getClient());
				appointmentDto.setCreatedDate(appointment.getCreatedDate());
				appointmentDto.setUpdatedDate(appointment.getUpdatedDate());
				appointmentDto.setInstructorAvailability(appointment.getInstructorAvailability());
				appointmentDto.setStatus(appointment.getStatus().toString());
				appointmentDto.setId(appointment.getId());
				appointmentDto.setInstructor(appointment.getInstructor());
				appointmentDtos.add(appointmentDto);
			}

			if (!appointments.isEmpty()) {
				appointmentResponseDto.setAppointments(appointmentDtos);
				appointmentResponseDto.setStatus(HttpStatus.OK.name());
				appointmentResponseDto.setMessage("Successfully retrieve appointmnets.");
			} else {
				appointmentResponseDto.setMessage("No matching appoinments.");
				appointmentResponseDto.setStatus(HttpStatus.NO_CONTENT.name());
			}
			return new ResponseEntity<>(appointmentResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void sendEmailNotification(Appointment appointment,String subject,String body) {
		 String recipientEmail = appointment.getClient().getEmail(); 
		 emailService.sendEmail(recipientEmail,subject, body);
	}
	
}

