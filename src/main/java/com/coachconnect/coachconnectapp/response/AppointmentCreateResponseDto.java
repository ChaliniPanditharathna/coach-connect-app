package com.coachconnect.coachconnectapp.response;

import com.coachconnect.coachconnectapp.model.Appointment;

public class AppointmentCreateResponseDto {

	private String message;

	private Appointment appointment;

	private String status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
