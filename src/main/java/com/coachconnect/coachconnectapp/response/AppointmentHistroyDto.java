package com.coachconnect.coachconnectapp.response;

import java.util.List;

public class AppointmentHistroyDto {

	private String message;

	private List<AppointmentDto> appointments;

	private String status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AppointmentDto> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentDto> appointments) {
		this.appointments = appointments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
