package com.coachconnect.coachconnectapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	Rating findByClientIdAndInstructorId(long currentUserId, long instructorId);
}
