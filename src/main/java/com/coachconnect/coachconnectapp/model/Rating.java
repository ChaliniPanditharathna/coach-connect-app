package com.coachconnect.coachconnectapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "points")
	private double points;
	

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    //private User client;
	private Client client;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    //private User instructor;
	private Instructor instructor;
	
	
	
	public Rating() {}

	public Rating(String comment, double points, Client client, Instructor instructor) {
		super();
		this.comment = comment;
		this.points = points;
		this.client = client;
		this.instructor = instructor;
	}
	
	public Rating(String comment, double points) {
		super();
		this.comment = comment;
		this.points = points;
			
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Client getCliendId() {
		return client;
	}

	public void setCliendId(Client client) {
		this.client = client;
	}

	public Instructor getInstructorId() {
		return instructor;
	}

	public void setInstructorId(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
}