package com.coachconnect.coachconnectapp.response;

import java.util.List;


import com.coachconnect.coachconnectapp.model.Rating;

public class RatingCreateResponseDto {

	private String message;
	
	private String status;
	
	private Rating ratings;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Rating getRatings() {
		return ratings;
	}

	public void setRatings(Rating ratings) {
		this.ratings = ratings;
	}

	

}
