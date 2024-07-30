package com.indigo.indigosan.DTO;

public class FlightStatusEmailRequest {
    private String flightId;
    private String email;

    // Default constructor
    public FlightStatusEmailRequest() {}

    // Parameterized constructor
    public FlightStatusEmailRequest(String flightId, String email) {
        this.flightId = flightId;
        this.email = email;
    }

    // Getter for flightId
    public String getFlightId() {
        return flightId;
    }

    // Setter for flightId
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
}

