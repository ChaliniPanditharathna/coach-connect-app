package com.coachconnect.coachconnectapp.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "userId")
	private long userId;
	
	@Column(name = "fName")
	private String fName;

	@Column(name = "lName")
	private String lName;

	@Column(name = "email")
	private String email;

	@Column(name = "birthDate")
	private LocalDate birthDate;

	@Column(name = "unitNo")
	private String unitNo;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "postalCode")
	private String postalCode;

	@Column(name = "gender")
	private String gender;

	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<Appointment>  appointment = new HashSet <>();
	
	public Client() {

	}

	public Client(Long userId, String fName, String lName, String email, LocalDate birthDate, String unitNo, String street,
			String city, String postalCode, String gender, LocalDateTime createdDate, LocalDateTime updatedDate,
			String description) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.birthDate = birthDate;
		this.unitNo = unitNo;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.gender = gender;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.description = description;
	}
	
	public Client(long userId, String fName, String lName, String email, LocalDate birthDate, String unitNo,
			String street, String city, String postalCode, String gender, LocalDateTime createdDate,
			String description) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.birthDate = birthDate;
		this.unitNo = unitNo;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.gender = gender;
		this.createdDate = createdDate;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
		this.appointment = appointment;
	}
	
	

}