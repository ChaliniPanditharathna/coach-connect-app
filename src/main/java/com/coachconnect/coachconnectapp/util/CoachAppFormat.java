package com.coachconnect.coachconnectapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CoachAppFormat {
	
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static LocalDateTime stringToLocalDateTime(String dateString) {
		// Example string representing date and time
        //String dateString = "2024-02-21 10:15:30";

        // Define the formatter to match the pattern of the string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        // Parse the string to a LocalDateTime object using the formatter
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

        // Print the LocalDateTime object
        //System.out.println("Parsed LocalDateTime: " + dateTime);
		return dateTime;
	}

}
