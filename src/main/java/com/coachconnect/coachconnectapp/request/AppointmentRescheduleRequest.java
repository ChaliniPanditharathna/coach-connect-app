package com.coachconnect.coachconnectapp.request;

import java.time.LocalDate;

public class AppointmentRescheduleRequest {
    private Long appointmentId;
    private LocalDate newDate;

    // Constructors, getters, and setters

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }
}
