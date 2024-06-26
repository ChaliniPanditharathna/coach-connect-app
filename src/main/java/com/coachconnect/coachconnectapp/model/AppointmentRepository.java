package com.coachconnect.coachconnectapp.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.coachconnect.coachconnectapp.response.AppointmentDto;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findAll();
    List<Appointment> findByStatus(EnumStatus status);
    List<Appointment> findByClientId(Long clientId);
    List<Appointment> findByInstructorId(Long instructorId);
    
	Iterable<Appointment> findByDateAfterAndClientId(LocalDate now, long id);
	Iterable<Appointment> findByDateBeforeAndClientId(LocalDate now, long id);
	Iterable<Appointment> findByDateAfterAndInstructorId(LocalDate now, long id);
	Iterable<Appointment> findByDateBeforeAndInstructorId(LocalDate now, long id);
	
    
   
}
