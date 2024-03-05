package com.coachconnect.coachconnectapp.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.ClientInstructor;
import com.coachconnect.coachconnectapp.model.InstructorAvailability;

public class AppointmentDto {

	private long id;

	private String status;

	private LocalDate date;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private InstructorAvailability instructorAvailability;

	private ClientInstructor clientInstructor;

	private Client client;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public InstructorAvailability getInstructorAvailability() {
		return instructorAvailability;
	}

	public void setInstructorAvailability(InstructorAvailability instructorAvailability) {
		this.instructorAvailability = instructorAvailability;
	}

	public ClientInstructor getClientInstructor() {
		return clientInstructor;
	}

	public void setClientInstructor(ClientInstructor clientInstructor) {
		this.clientInstructor = clientInstructor;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
