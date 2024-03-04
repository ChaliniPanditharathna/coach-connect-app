package com.coachconnect.coachconnectapp.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Long id;
	
	@Column(name = "dateTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	
	@Column(name = "createdDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	
	@Column(name = "updatedDate ")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedDate;
	
	@Column(name = "Status")
    private EnumStatus status;
	
	@ManyToOne
    @JoinColumn(name = "Instructor_Availability_ID")
    private InstructorAvailability instructorAvailability;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
	
	@Column(name = "RejectedReason")
	private String rejectedReason;
    
    public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}

	@ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

	public Appointment() {
		
	}

	public Appointment(LocalDateTime dateTime, LocalDateTime createdDate, LocalDateTime updatedDate, EnumStatus status,
			InstructorAvailability instructorAvailability) {
		super();
		this.dateTime = dateTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.instructorAvailability = instructorAvailability;
	}

	public Appointment(LocalDateTime dateTime, LocalDateTime createdDate, LocalDateTime updatedDate,
			EnumStatus status, User client, User instructor) {
		this.dateTime = dateTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.client = client;
		this.instructor = instructor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public InstructorAvailability getInstructorAvailability() {
		return instructorAvailability;
	}

	public void setInstructorAvailability(InstructorAvailability instructorAvailability) {
		this.instructorAvailability = instructorAvailability;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", dateTime=" + dateTime + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", status=" + status + ", instructorAvailability=" + instructorAvailability
				+ ", client=" + client + ", instructor=" + instructor + "]";
	}
	
	

}
