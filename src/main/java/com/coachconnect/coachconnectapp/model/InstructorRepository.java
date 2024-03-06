package com.coachconnect.coachconnectapp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

	List<Instructor> findAll();
	
	List<Instructor> findByExpertiseContainingIgnoreCaseOrCityContainingIgnoreCase(String expertise, String city);

	Instructor findByUserId(long currentUserId);

}
