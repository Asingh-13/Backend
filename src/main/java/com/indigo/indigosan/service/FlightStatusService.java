package com.indigo.indigosan.service;

import com.indigo.indigosan.model.FlightStatus;
import com.indigo.indigosan.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class FlightStatusService {

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private FlightStatusEmailService flightStatusEmailService;

    public FlightStatus updateActualTimesAndStatus(String flightId, String status, Date actualDeparture, Date actualArrival) throws Exception {
        Optional<FlightStatus> flightStatusOptional = statusRepo.findFlightByFlightId(flightId).stream().findFirst();

        if (!flightStatusOptional.isPresent()) {
            throw new Exception("Flight not found");
        }

        FlightStatus flightStatus = flightStatusOptional.get();
        flightStatus.setStatus(status);
        flightStatus.setActualDeparture(actualDeparture);
        flightStatus.setActualArrival(actualArrival);

        // Save the updated status
        FlightStatus updatedStatus = statusRepo.save(flightStatus);

        // Send email notification
        flightStatusEmailService.sendFlightStatusUpdateEmail(flightStatus);

        return updatedStatus;
    }
}
