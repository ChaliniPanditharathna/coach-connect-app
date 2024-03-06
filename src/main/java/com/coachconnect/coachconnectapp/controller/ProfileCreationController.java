package com.coachconnect.coachconnectapp.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachconnect.coachconnectapp.model.Client;
import com.coachconnect.coachconnectapp.model.ClientRepository;
import com.coachconnect.coachconnectapp.model.EnumRole;
import com.coachconnect.coachconnectapp.model.Instructor;
import com.coachconnect.coachconnectapp.model.InstructorAvailability;
import com.coachconnect.coachconnectapp.model.InstructorAvailabilityRepository;
import com.coachconnect.coachconnectapp.model.InstructorRepository;
import com.coachconnect.coachconnectapp.model.User;
import com.coachconnect.coachconnectapp.model.UserRepository;
import com.coachconnect.coachconnectapp.request.InstructorAvailabilityRequest;
import com.coachconnect.coachconnectapp.request.ProfileUpdateRequest;
import com.coachconnect.coachconnectapp.response.MessageResponse;

@RestController
@RequestMapping("/api/coachconnect")
public class ProfileCreationController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	InstructorRepository instructorRepository;

	@Autowired
	ClientRepository clientRepository;
	
	
	@Autowired
	InstructorAvailabilityRepository instructorAvailabilityRepository;
	

	@PostMapping("/updateProfile/{id}")
	public ResponseEntity<?> insertProfileDetails(@PathVariable("id") long id,
			@RequestBody ProfileUpdateRequest profileUpdateRequest) {

		Optional<User> userData = userRepository.findById(id);

		if (userRepository.existsByUsername(userData.get().getUsername()) == false) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: The user is does not exist!"));
		}

		if (userRepository.existsByEmail(userData.get().getEmail()) == false) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: The user is does not exist!"));
		}

		if (userData.isPresent()) {

			if (userData.get().getRoles().stream().anyMatch(role -> role.getName().equals(EnumRole.ROLE_INSTRUCTOR))) {

				Instructor instructorData = instructorRepository.findByUserId(userData.get().getId());

				if (instructorData == null) {
					Instructor instructor = instructorRepository.save(new Instructor(userData.get().getId(), profileUpdateRequest.getfName(),
							profileUpdateRequest.getlName(), userData.get().getEmail(),
							profileUpdateRequest.getBirthDate(), profileUpdateRequest.getUnitNo(),
							profileUpdateRequest.getStreet(), profileUpdateRequest.getCity(),
							profileUpdateRequest.getPostalCode(), profileUpdateRequest.getGender(), LocalDateTime.now(),
							profileUpdateRequest.getQualification(), profileUpdateRequest.getExpertise()));
					
					System.out.println("Instructor Availability : " + profileUpdateRequest.getInstructorAvailability().getWeekDay());
					
					instructorAvailabilityRepository.save(new InstructorAvailability(profileUpdateRequest.getInstructorAvailability().getWeekDay(),
							profileUpdateRequest.getInstructorAvailability().getStartTime(), profileUpdateRequest.getInstructorAvailability().getEndTime(), instructor));
					
					
					return ResponseEntity.ok(new MessageResponse("Instructor updated successfully!"));
				} else {

					instructorData.setfName(profileUpdateRequest.getfName());
					instructorData.setlName(profileUpdateRequest.getlName());
					instructorData.setEmail(profileUpdateRequest.getEmail());
					instructorData.setBirthDate(profileUpdateRequest.getBirthDate());
					instructorData.setUnitNo(profileUpdateRequest.getUnitNo());
					instructorData.setStreet(profileUpdateRequest.getStreet());
					instructorData.setCity(profileUpdateRequest.getCity());
					instructorData.setPostalCode(profileUpdateRequest.getPostalCode());
					instructorData.setGender(profileUpdateRequest.getGender());
					instructorData.setQualification(profileUpdateRequest.getQualification());
					instructorData.setExpertise(profileUpdateRequest.getExpertise());
					
					instructorAvailabilityRepository.save(new InstructorAvailability(profileUpdateRequest.getInstructorAvailability().getWeekDay(),
							profileUpdateRequest.getInstructorAvailability().getStartTime(), profileUpdateRequest.getInstructorAvailability().getEndTime(), instructorData));

					return new ResponseEntity<>(instructorRepository.save(instructorData), HttpStatus.OK);
				}

			} else if (userData.get().getRoles().stream()
					.anyMatch(role -> role.getName().equals(EnumRole.ROLE_CLIENT))) {

				Optional<Client> clientData = clientRepository.findByUserId(userData.get().getId());

				if (clientData.isEmpty()) {
					clientRepository.save(new Client(userData.get().getId(), profileUpdateRequest.getfName(),
							profileUpdateRequest.getlName(), userData.get().getEmail(),
							profileUpdateRequest.getBirthDate(), profileUpdateRequest.getUnitNo(),
							profileUpdateRequest.getStreet(), profileUpdateRequest.getCity(),
							profileUpdateRequest.getPostalCode(), profileUpdateRequest.getGender(), LocalDateTime.now(),
							profileUpdateRequest.getDescription()));

					return ResponseEntity.ok(new MessageResponse("Client updated successfully!"));
				} else {
					Client updateClient = clientData.get();
					updateClient.setfName(profileUpdateRequest.getfName());
					updateClient.setlName(profileUpdateRequest.getlName());
					updateClient.setEmail(profileUpdateRequest.getEmail());
					updateClient.setBirthDate(profileUpdateRequest.getBirthDate());
					updateClient.setUnitNo(profileUpdateRequest.getUnitNo());
					updateClient.setStreet(profileUpdateRequest.getStreet());
					updateClient.setCity(profileUpdateRequest.getCity());
					updateClient.setPostalCode(profileUpdateRequest.getPostalCode());
					updateClient.setGender(profileUpdateRequest.getGender());
					updateClient.setDescription(profileUpdateRequest.getDescription());

					return new ResponseEntity<>(clientRepository.save(updateClient), HttpStatus.OK);
				}

			} else {
				return ResponseEntity.ok(new MessageResponse("Profile can not be updated!"));
			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: The user is does not exist!"));
		}
	}
}
