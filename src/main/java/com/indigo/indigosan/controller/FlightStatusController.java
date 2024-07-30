package com.indigo.indigosan.controller;

import com.indigo.indigosan.DTO.FlightStatusEmailRequest;
import com.indigo.indigosan.model.FlightStatus;
import com.indigo.indigosan.service.FlightStatusEmailService;
import com.indigo.indigosan.service.FlightStatusService;
import com.indigo.indigosan.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FlightStatusController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private FlightStatusEmailService flightStatusEmailService;

    @Autowired
    private FlightStatusService flightStatusService;

    // Endpoint to get flight status by flight ID
    @GetMapping("/flightStatus/{flightId}")
    public List<FlightStatus> getFlightStatusById(@PathVariable String flightId) {
        return statusService.getFlightStatusByID(flightId);
    }

    // Endpoint to send flight status email
    @PostMapping("/sendFlightStatus")
    public ResponseEntity<String> sendFlightStatusEmail(@RequestBody FlightStatusEmailRequest request) {
        try {
            flightStatusEmailService.sendFlightStatusEmail(request.getFlightId(), request.getEmail());
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send email. " + e.getMessage());
        }
    }

    // Endpoint to update actual departure and arrival times and status of a flight
    @PutMapping("/{flightId}/update-actual-times")
    public ResponseEntity<?> updateActualTimesAndStatus(
            @PathVariable String flightId,
            @RequestParam String status,
            @RequestParam String actualDeparture,
            @RequestParam String actualArrival) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date actualDepartureTime = dateFormat.parse(actualDeparture);
            Date actualArrivalTime = dateFormat.parse(actualArrival);

            FlightStatus updatedStatus = flightStatusService.updateActualTimesAndStatus(flightId, status, actualDepartureTime, actualArrivalTime);
            return ResponseEntity.ok(updatedStatus);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid date format. Please use 'yyyy-MM-dd'T'HH:mm:ss'.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to update flight status. " + e.getMessage());
        }
    }
}
