package com.coachconnect.coachconnectapp.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findAll();
	List<Appointment> findByStatus(String status);

}
