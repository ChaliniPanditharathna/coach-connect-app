package com.coachconnect.coachconnectapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "Rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ratingValue")
	private int value;

	@Column(name = "clientId")
	private long clientId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "instructor_id", nullable = false)
	@JsonIgnore
	private Instructor instructor;

	public Rating() {
	}

	public Rating(int value, long clientId, Instructor instructor) {
		super();
		this.value = value;
		this.clientId = clientId;
		this.instructor = instructor;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

}
