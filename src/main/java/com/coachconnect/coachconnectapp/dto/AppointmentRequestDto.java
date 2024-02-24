package com.coachconnect.coachconnectapp.dto;

import java.time.LocalDate;

import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorAvailability;
import com.coachconnect.coachconnectapp.model.Client;



public class AppointmentRequestDto {

    private String status;
	
	private LocalDate date;
	
	private Instructor instructor;
	
	private Client client;
	
	private InstructorAvailability instructorAvailability;

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

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public InstructorAvailability getInstructorAvailability() {
		return instructorAvailability;
	}

	public void setInstructorAvailability(InstructorAvailability instructorAvailability) {
		this.instructorAvailability = instructorAvailability;
	}


	
	
}
