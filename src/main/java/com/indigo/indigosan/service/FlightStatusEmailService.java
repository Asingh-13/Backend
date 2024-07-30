package com.indigo.indigosan.service;

import com.indigo.indigosan.model.Notification;
import com.indigo.indigosan.model.FlightStatus;
import com.indigo.indigosan.repo.NotificationRepository;
import com.indigo.indigosan.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlightStatusEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendFlightStatusEmail(String flightId, String email) throws Exception {
        FlightStatus flightStatus = statusRepo.findFlightByFlightId(flightId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new Exception("Flight not found"));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Flight Status for " + flightId);
        message.setText("Flight " + flightId + " is currently " + flightStatus.getStatus() +
                ". Scheduled departure: " + flightStatus.getScheduledDeparture() +
                ". Scheduled arrival: " + flightStatus.getScheduledArrival() + ".");

        emailSender.send(message);

        // Save notification
        Notification notification = new Notification();
        notification.setFlightId(flightId);
        notification.setMessage(message.getText());
        notification.setMethod("Email");
        notification.setRecipient(email);
        notification.setTimestamp(new Date());
        notificationRepository.save(notification);
    }

    public void sendFlightStatusUpdateEmail(FlightStatus flightStatus) {
        // Fetch all notifications for the given flight ID
        List<Notification> notifications = notificationRepository.findByFlightId(flightStatus.getFlightId());

        for (Notification notification : notifications) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getRecipient());
            message.setSubject("Updated Flight Status for " + flightStatus.getFlightId());
            message.setText("Flight " + flightStatus.getFlightId() + " status has been updated to " + flightStatus.getStatus() +
                    ". Actual departure: " + flightStatus.getActualDeparture() +
                    ". Actual arrival: " + flightStatus.getActualArrival() + ".");

            emailSender.send(message);

            // Update and save notification
            notification.setMessage(message.getText());
            notification.setTimestamp(new Date());
            notificationRepository.save(notification);
        }
    }
}
