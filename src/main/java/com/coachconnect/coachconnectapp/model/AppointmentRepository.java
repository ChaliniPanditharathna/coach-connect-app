package com.coachconnect.coachconnectapp.model;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	Iterable<Appointment> findByDateAfter(LocalDate now);

	Iterable<Appointment> findByDateBefore(LocalDate now);
	

	
}
