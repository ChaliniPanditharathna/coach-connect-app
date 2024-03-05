package com.coachconnect.coachconnectapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInstructorRepository extends JpaRepository<ClientInstructor, Long>  {

	//List<ClientInstructor> findByClientInstructorIdContainingIgnoreCase(Client client);

	List<ClientInstructor> findByInstructorId(long id);
	

	
}
