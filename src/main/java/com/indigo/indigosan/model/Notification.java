package com.indigo.indigosan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Or another suitable strategy
    private Long id;
    private String flightId;
    private String message;
    private String method;
    private String recipient;
    private Date timestamp;

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
