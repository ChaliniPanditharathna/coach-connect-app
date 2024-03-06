package com.coachconnect.coachconnectapp.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	List<Rating> findAll();
	List<Rating> findByComment(String comment);
	List<Rating> findByPoints(double points);
	//List<Rating> findByInstructorId(Instructor instructorId);
	//List<Rating> findByClientId(Client clientId);;
	List<Rating> findById(long id);

}
