package com.coachconnect.coachconnectapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.coachconnect.coachconnectapp.request.AppointmentRequestDto;
import com.coachconnect.coachconnectapp.response.AppointmentCreateResponseDto;
import com.coachconnect.coachconnectapp.response.AppointmentDto;
import com.coachconnect.coachconnectapp.response.AppointmentHistroyDto;
import com.coachconnect.coachconnectapp.security.jwt.JwtUtils;
import com.coachconnect.coachconnectapp.sendemail.EmailService;
import com.coachconnect.coachconnectapp.service.impl.UserDetailsImpl;
import com.coachconnect.coachconnectapp.util.CoachAppFormat;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@RestController
@RequestMapping("/api/coachconnect")
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepo;

	@Autowired
	InstructorRepository instructorRepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private EmailService emailService;
	


	/*
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
				appointments = appointmentRepo.findByInstructorId(instructorId);
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
	}*/

	//End point to approve pending appointments
//	@PutMapping("/appointment/approve")
//	public ResponseEntity<Appointment> approvePendingAppointments(
//			@RequestBody AppointmentApproveRequest appointmentApproveRequest) {
//		System.out.println(JwtUtils.getCurrentUserId());
//		
//		long currentUserId = JwtUtils.getCurrentUserId(); 
//	    
//		// Fetch the pending appointment
//        Appointment appointment = appointmentRepo.findById(appointmentApproveRequest.getAppointmentId())
//                .orElse(null);
//        System.out.println(appointmentApproveRequest.getAppointmentId());
//
//        if (appointment != null && appointment.getStatus() == EnumStatus.STATUS_PENDING) {
//            if (appointment.getClientInstructor().getInstructor().getUserId() == currentUserId) {
//                appointment.setStatus(EnumStatus.STATUS_APPROVED);
//                appointment.setUpdatedDate(LocalDateTime.now()); 
//                appointmentRepo.save(appointment); 
//
//                // Send email notification
//                String subject = "Appointment Approved";
//                String body = "Your appointment has been approved.";
//                sendEmailNotification(appointment, subject, body);
//
//                return new ResponseEntity<>(appointment, HttpStatus.OK);
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
//            }
//        } else {
//        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//		
//	}
	
	
	@PostMapping("/appointment")
	public ResponseEntity<AppointmentCreateResponseDto> createAppointment(
			@RequestBody AppointmentRequestDto appointment) {

		try {
			Appointment _appointment = appointmentRepo
					.save(new Appointment(LocalDateTime.now(),LocalDateTime.now() , EnumStatus.valueOf(appointment.getStatus().toString()),appointment.getDate(),
							appointment.getInstructorAvailability(),  appointment.getClient(), appointment.getInstructor(), null));

			AppointmentCreateResponseDto appointmentResponseDto = new AppointmentCreateResponseDto();
			appointmentResponseDto.setAppointment(_appointment);
			appointmentResponseDto.setMessage("Sucsessfully created appointment.");
			appointmentResponseDto.setStatus(HttpStatus.CREATED.name());

			return new ResponseEntity<>(appointmentResponseDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 //End point to reject pending appointments
//		@PutMapping("/appointment/reject")
//		public ResponseEntity<Appointment> rejectPendingAppointments(
//				@RequestBody AppointmentRejectRequest appointmentRejectRequest) {
//			System.out.println(JwtUtils.getCurrentUserId());
//			
//			long currentUserId = JwtUtils.getCurrentUserId(); 
//		    
//			// Fetch the pending appointment
//	        Appointment appointment = appointmentRepo.findById(appointmentRejectRequest.getAppointmentId())
//	                .orElse(null);
//	        System.out.println(appointmentRejectRequest.getAppointmentId());
//
//	        if (appointment != null && appointment.getStatus() == EnumStatus.STATUS_PENDING) {
//	            if (appointment.getClientInstructor().getInstructor().getUserId() == currentUserId) {
//	                appointment.setStatus(EnumStatus.STATUS_REJECTED);
//	                appointment.setRejectedReason(appointmentRejectRequest.getRejectedReason());
//	                appointment.setUpdatedDate(LocalDateTime.now()); 
//	                appointmentRepo.save(appointment);
//	                
//	                ClientInstructor clientInstructor = appointment.getClientInstructor();
//	                clientInstructorRepository.delete(clientInstructor);
//
//	                // Send email notification
//		            String subject = "Appointment has been rejected";
//					String body = "Your appointment has been rejected.";
//					sendEmailNotification(appointment, subject, body);
//
//	                return new ResponseEntity<>(appointment, HttpStatus.OK);
//	            } else {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
//	            }
//	        } else {
//	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//		}
//	
	
	//Endpoint for reschedule appointment
//	@PutMapping("/appointment/reschedule")
//	public ResponseEntity<Appointment> reschedulePendingAppointments(
//	        @RequestBody AppointmentRescheduleRequest appointmentRescheduleRequest) {
//	    System.out.println(JwtUtils.getCurrentUserId());
//		
//		long currentUserId = JwtUtils.getCurrentUserId(); 
//	    
//		// Fetch the pending appointment
//        Appointment appointment = appointmentRepo.findById(appointmentRescheduleRequest.getAppointmentId())
//                .orElse(null);
//        System.out.println(appointmentRescheduleRequest.getAppointmentId());
//
//        if (appointment != null && appointment.getStatus() == EnumStatus.STATUS_PENDING) {
//            if (appointment.getClientInstructor().getInstructor().getUserId() == currentUserId) {
//                appointment.setStatus(EnumStatus.STATUS_APPROVED);
//                appointment.setDate(appointmentRescheduleRequest.getNewDate());
//                appointment.setUpdatedDate(LocalDateTime.now()); 
//                appointmentRepo.save(appointment); 
//
//                // Send email notification
//	           // String subject = "Appointment has been rescheduled";
//				//String body = "Your appointment has been rescheduled.";
//				//sendEmailNotification(appointment, subject, body);
//
//                return new ResponseEntity<>(appointment, HttpStatus.OK);
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
//            }
//        } else {
//        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//	   
//	}
	
	@GetMapping("/appointment/history")
	public ResponseEntity<AppointmentHistroyDto> getAppoinmentByCurrentDateAndInstructorId(
			@RequestParam(value = "searchKey", required = true) String searchKey) {
		
		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();
		AppointmentHistroyDto appointmentResponseDto = new AppointmentHistroyDto();
		try {
			Optional<Instructor> instructor = instructorRepo.findByUserId(JwtUtils.getCurrentUserId());

	

			if ("previous".equals(searchKey)) {
				appointmentRepo.findByDateBeforeAndInstructorId(LocalDate.now(), instructor.get().getId()).forEach(appointments::add);

			}
			if ("upcomming".equals(searchKey)) {
				appointmentRepo.findByDateAfterAndInstructorId(LocalDate.now(),instructor.get().getId() ).forEach(appointments::add);
			}
			
			for (Appointment appointment : appointments) {
				System.out.println("Testdidnd " + appointment.getDate());
				AppointmentDto appointmentDto = new AppointmentDto();
				appointmentDto.setDate(appointment.getDate());
				appointmentDto.setClient(appointment.getClient());
				appointmentDto.setCreatedDate(appointment.getCreatedDate());
				appointmentDto.setUpdatedDate(appointment.getUpdatedDate());
				appointmentDto.setInstructorAvailability(appointment.getInstructorAvailability());
				appointmentDto.setStatus(appointment.getStatus().toString());
				appointmentDto.setId(appointment.getId());
				appointmentDtos.add(appointmentDto);
			}

			if (!appointments.isEmpty()) {
				appointmentResponseDto.setAppointments(appointmentDtos);
				appointmentResponseDto.setStatus(HttpStatus.OK.name());
				appointmentResponseDto.setMessage("Successfully retrive appointmnets.");
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
		//String recipientEmail = appointment.getClient().getEmail(); 
		String recipientEmail = "gchinthaka@gmail.com"; 
		 emailService.sendEmail(recipientEmail,subject, body);
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

class AppointmentRejectRequest {
    private Long appointmentId;
    private String rejectedReason;

   
    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }
}

class AppointmentRescheduleRequest {
    private Long appointmentId;
    private LocalDate newDate;

    // Constructors, getters, and setters

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }
}