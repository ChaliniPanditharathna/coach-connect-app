package com.coachconnect.coachconnectapp.request;

import java.time.LocalDate;

import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.EnumStatus;
import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorAvailability;
import com.coachconnect.coachconnectapp.model.User;



public class AppointmentRequestDto {

    private EnumStatus status;
	
	private LocalDate date;
	
	private Instructor instructor;
	
	private Client client;
	
	private InstructorAvailability instructorAvailability;


	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
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
