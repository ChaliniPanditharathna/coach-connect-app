package com.coachconnect.coachconnectapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AppointmentID")
	private Long id;

	@Column(name = "createdDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;

	@Column(name = "updatedDate ")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedDate;

	@Column(name = "Status")
	private EnumStatus status;

	@Column(name = "Date")
	private LocalDate date;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "instructorAvailability_Id", nullable = false)
	private InstructorAvailability instructorAvailability;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_Id", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "instructor_Id", nullable = false)
	private Instructor instructor;

	@Column(name = "RejectedReason")
	private String rejectedReason;

	public Appointment(LocalDateTime createdDate, LocalDateTime updatedDate, EnumStatus status, LocalDate date,
			InstructorAvailability instructorAvailability, Client client, Instructor instructor,
			String rejectedReason) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.date = date;
		this.instructorAvailability = instructorAvailability;
		this.client = client;
		this.instructor = instructor;
		this.rejectedReason = rejectedReason;
	}

	public Appointment(LocalDate dateTime, LocalDateTime createdDate, LocalDateTime updatedDate, EnumStatus status,
			Client client, Instructor instructor) {
		this.date = dateTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		// this.client = client;
		// this.instructor = instructor;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "Appointment [id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", status="
				+ status + ", instructorAvailability=" + instructorAvailability;
		// + ", client=" + client + "]";
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
