package com.coachconnect.coachconnectapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "status")
	private String status;

	@Column(name = "Date")
	private LocalDate date;

	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "instructorAvailability_Id", nullable = false)
	private InstructorAvailability instructorAvailability;

	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "clientInstructor_id", nullable = false)
	private ClientInstructor clientInstructor;

	public Appointment() {}

	public Appointment(String status, LocalDateTime createdDate, LocalDateTime updatedDate,
			InstructorAvailability instructorAvailability, LocalDate date, ClientInstructor clientInstructor) {
		super();
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.instructorAvailability = instructorAvailability;
		this.date = date;
		this.clientInstructor = clientInstructor;
	}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ClientInstructor getClientInstructor() {
		return clientInstructor;
	}

	public void setClientInstructor(ClientInstructor clientInstructor) {
		this.clientInstructor = clientInstructor;
	}

}
