package com.coachconnect.coachconnectapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AppointmentID")
	private long id;
	
	@Column(name = "dateTime")
	private LocalDateTime dateTime;
	
	@Column(name = "createdDate")
	private LocalDateTime createdDate;
	
	@Column(name = "updatedDate ")
	private LocalDateTime updatedDate;
	
	@Column(name = "Status")
    private String status;
	
	@ManyToOne
    @JoinColumn(name = "Instructor_Availability_ID")
    private InstructorAvailability instructorAvailability;

	public Appointment() {
		
	}

	public Appointment(LocalDateTime dateTime, LocalDateTime createdDate, LocalDateTime updatedDate, String status,
			InstructorAvailability instructorAvailability) {
		super();
		this.dateTime = dateTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.instructorAvailability = instructorAvailability;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InstructorAvailability getInstructorAvailability() {
		return instructorAvailability;
	}

	public void setInstructorAvailability(InstructorAvailability instructorAvailability) {
		this.instructorAvailability = instructorAvailability;
	}
	
	

}
