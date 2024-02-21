package com.coachconnect.coachconnectapp.model;


import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long InstructorID;
	
	@Column(name = "fName")
	private String fName;
	
	@Column(name = "lName")
	private String lName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "birthDate")
	private Date birthDate ;
	
	@Column(name = "unitNo")
	private String unitNo ;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city; 
	
	@Column(name = "postalCode")
	private String postalCode; 
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "createdDate ")
	private LocalDateTime createdDate ; 
	
	@Column(name = "updatedDate ")
	private LocalDateTime updatedDate ; 
	
	@Column(name = "qualification")
	private String qualification; 
	
	@Column(name = "expertise")
	private String expertise ; 
	
	@Column(name = "status")
	private String status;
	
	public Instructor() {
		
	}

	public Instructor( String fName, String lName, String email, Date birthDate, String unitNo,
			String street, String city, String postalCode, String gender, LocalDateTime createdDate,
			LocalDateTime updatedDate, String qualification, String expertise, String status) {
		super();
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
		this.qualification = qualification;
		this.expertise = expertise;
		this.status = status;
	}

	public long getInstructorID() {
		return InstructorID;
	}

	public void setInstructorID(long instructorID) {
		InstructorID = instructorID;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
