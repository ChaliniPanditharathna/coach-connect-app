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
	
//	@ManyToOne
//    @JoinColumn(name = "Instructor_Availability_ID")
//    private InstructorAvailability instructorAvailability;
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "instructorAvailability_Id", nullable = false)
	private InstructorAvailability instructorAvailability;

	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "clientInstructor_id", nullable = false)
	@JsonIgnore
	private ClientInstructor clientInstructor;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
	
	@Column(name = "RejectedReason")
	private String rejectedReason;
    


	public Appointment() {
		
	}

	public Appointment( LocalDateTime createdDate, LocalDateTime updatedDate, EnumStatus status,
			InstructorAvailability instructorAvailability, LocalDate date, ClientInstructor clientInstructor) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.date = date;
		this.instructorAvailability = instructorAvailability;
		this.clientInstructor = clientInstructor;
	}
	

	public Appointment(LocalDate dateTime, LocalDateTime createdDate, LocalDateTime updatedDate,
			EnumStatus status, User client, Instructor instructor) {
		this.date = dateTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.client = client;
		//this.instructor = instructor;
		
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
		return "Appointment [id=" + id + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", status=" + status + ", instructorAvailability=" + instructorAvailability
				+ ", client=" + client + "]";
	}

	public ClientInstructor getClientInstructor() {
		return clientInstructor;
	}

	public void setClientInstructor(ClientInstructor clientInstructor) {
		this.clientInstructor = clientInstructor;
	}
	
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
	

}
