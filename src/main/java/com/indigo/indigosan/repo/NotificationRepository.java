package com.indigo.indigosan.repo;

import com.indigo.indigosan.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByFlightId(String flightId);
}

