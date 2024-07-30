package com.indigo.indigosan.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightId;
    private String airline;
    private String status;
    private String departureGate;
    private String arrivalGate;
    private Date scheduledDeparture;
    private Date scheduledArrival;
    private Date actualDeparture;
    private Date actualArrival;

}
