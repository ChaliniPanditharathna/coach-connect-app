package com.coachconnect.coachconnectapp.model;

import java.sql.Time;

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
@Table(name = "Instructor_Availability")
public class InstructorAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "weekDay")
	private String weekDay;

	@Column(name = "startTime")
	private String startTime;

	@Column(name = "endTime")
	private String endTime;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "instructor_id", nullable = false)
	@JsonIgnore
	private Instructor instructor;

	public InstructorAvailability() {

	}

	public InstructorAvailability(String weekDay, String startTime, String endTime, Instructor instructor) {
		super();
		this.weekDay = weekDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.instructor = instructor;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}
