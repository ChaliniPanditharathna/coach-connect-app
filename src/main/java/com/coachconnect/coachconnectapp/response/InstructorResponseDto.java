package com.coachconnect.coachconnectapp.response;

import java.util.List;

import com.coachconnect.coachconnectapp.model.Instructor;

public class InstructorResponseDto {

	private String message;
	
	private String status;
	
	private List<Instructor> instructors;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}	
}
