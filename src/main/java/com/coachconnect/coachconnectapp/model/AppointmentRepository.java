package com.coachconnect.coachconnectapp.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findAll();
    List<Appointment> findByStatus(EnumStatus status);
    List<Appointment> findByClientId(Long clientId);
   // List<Appointment> findByInstructorId(Long instructorId);
   
}
