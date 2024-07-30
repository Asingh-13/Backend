package com.indigo.indigosan.service;

import com.indigo.indigosan.model.FlightStatus;
import com.indigo.indigosan.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepo repo;

    public List<FlightStatus> getFlightStatusByID(String flightId) {
        return repo.findFlightByFlightId(flightId);
    }



}
