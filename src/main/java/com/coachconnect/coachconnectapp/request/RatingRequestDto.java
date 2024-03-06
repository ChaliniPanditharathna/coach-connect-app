package com.coachconnect.coachconnectapp.request;

import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.Instructor;

public class RatingRequestDto {

	private String comment;
	private double points;
	private Client client;
	private Instructor instructor;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
}
