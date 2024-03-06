package com.coachconnect.coachconnectapp.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByUserId(long userId);
}
