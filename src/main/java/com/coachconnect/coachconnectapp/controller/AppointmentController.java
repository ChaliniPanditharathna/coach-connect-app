package com.coachconnect.coachconnectapp.controller;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.coachconnect.coachconnectapp.dto.AppointmentDto;
import com.coachconnect.coachconnectapp.dto.AppointmentHistroyDto;
import com.coachconnect.coachconnectapp.dto.AppointmentRequestDto;
import com.coachconnect.coachconnectapp.dto.AppointmentCreateResponseDto;
import com.coachconnect.coachconnectapp.model.Appointment;
import com.coachconnect.coachconnectapp.model.AppointmentRepository;
import com.coachconnect.coachconnectapp.model.ClientInstructor;
import com.coachconnect.coachconnectapp.model.ClientInstructorRepository;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	ClientInstructorRepository clientInstructorRepository;

	@PostMapping("/appointment")
	public ResponseEntity<AppointmentCreateResponseDto> createAppointment(
			@RequestBody AppointmentRequestDto appointment) {

		try {
			ClientInstructor _clientInstructor = clientInstructorRepository
					.save(new ClientInstructor(appointment.getInstructor(), appointment.getClient()));
			Appointment _appointment = appointmentRepository
					.save(new Appointment(appointment.getStatus(), LocalDateTime.now(), LocalDateTime.now(),
							appointment.getInstructorAvailability(), appointment.getDate(), _clientInstructor));

			AppointmentCreateResponseDto appointmentResponseDto = new AppointmentCreateResponseDto();
			appointmentResponseDto.setAppointment(_appointment);
			appointmentResponseDto.setMessage("Sucsessfully created appointment.");
			appointmentResponseDto.setStatus(HttpStatus.CREATED.name());

			return new ResponseEntity<>(appointmentResponseDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/appointment/history")
	public ResponseEntity<AppointmentHistroyDto> getAppoinmentByCurrentDateAndInstructorId(
			@RequestParam(value = "searchKey", required = true) String searchKey,
			@RequestParam(value = "instructorId", required = true) long instructorId) {

		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();
		List<Long> clientInstructorIdList = new ArrayList<>();
		List<ClientInstructor> clientInstructors = new ArrayList<>();
		AppointmentHistroyDto appointmentResponseDto = new AppointmentHistroyDto();
		List<Appointment> appointmentList = new ArrayList<>();
		try {
			clientInstructors = clientInstructorRepository.findByInstructorId(instructorId);

			for (ClientInstructor clientIns : clientInstructors) {
				clientInstructorIdList.add(clientIns.getId());
			}

			if ("previous".equals(searchKey)) {
				appointmentRepository.findByDateBefore(LocalDate.now()).forEach(appointments::add);

			}
			if ("upcomming".equals(searchKey)) {
				appointmentRepository.findByDateAfter(LocalDate.now()).forEach(appointments::add);
			}

			for (Appointment entity : appointments) {
				if (clientInstructorIdList.contains(entity.getClientInstructor().getId())) {
					appointmentList.add(entity);
				}
			}
			for (Appointment appointment : appointmentList) {
				AppointmentDto appointmentDto = new AppointmentDto();
				appointmentDto.setDate(appointment.getDate());
				appointmentDto.setClient(appointment.getClientInstructor().getClient());
				appointmentDto.setCreatedDate(appointment.getCreatedDate());
				appointmentDto.setUpdatedDate(appointment.getUpdatedDate());
				appointmentDto.setInstructorAvailability(appointment.getInstructorAvailability());
				appointmentDto.setStatus(appointment.getStatus());
				appointmentDto.setId(appointment.getId());
				appointmentDto.setClientInstructor(appointment.getClientInstructor());
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
}
