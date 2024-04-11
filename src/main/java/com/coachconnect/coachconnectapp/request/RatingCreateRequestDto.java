package com.coachconnect.coachconnectapp.request;



public class RatingCreateRequestDto {



	private int value;

	
	private long clientId;
	
	private long instructorId;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(long instructorId) {
		this.instructorId = instructorId;
	}

	
	
	

}
