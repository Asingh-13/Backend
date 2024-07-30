package com.indigo.indigosan.repo;

import com.indigo.indigosan.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface StatusRepo extends JpaRepository<FlightStatus, Long> {
    List<FlightStatus> findFlightByFlightId(String flightId);
        
}