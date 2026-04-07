package com.eduwebinars.service;

import com.eduwebinars.entity.Webinar;
import com.eduwebinars.repository.WebinarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WebinarService {

    @Autowired
    private WebinarRepository webinarRepository;

    public List<Webinar> getAllWebinars() {
        return webinarRepository.findAll();
    }

    public Optional<Webinar> getWebinarById(Long id) {
        return webinarRepository.findById(id);
    }

    public Webinar createWebinar(Webinar webinar) {
        webinar.setCurrentParticipants(0);
        return webinarRepository.save(webinar);
    }

    public Webinar updateWebinar(Long id, Webinar webinarDetails) {
        Webinar webinar = webinarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Webinar not found with id: " + id));

        webinar.setTitle(webinarDetails.getTitle());
        webinar.setDescription(webinarDetails.getDescription());
        webinar.setInstructor(webinarDetails.getInstructor());
        webinar.setDateTime(webinarDetails.getDateTime());
        webinar.setMaxParticipants(webinarDetails.getMaxParticipants());
        webinar.setMeetingLink(webinarDetails.getMeetingLink());
        webinar.setCategory(webinarDetails.getCategory());

        return webinarRepository.save(webinar);
    }

    public void deleteWebinar(Long id) {
        webinarRepository.deleteById(id);
    }

    public List<Webinar> findByCategory(String category) {
        return webinarRepository.findByCategory(category);
    }

    public List<Webinar> findByTitle(String title) {
        return webinarRepository.findByTitleContainingIgnoreCase(title);
    }

    public Webinar registerParticipant(Long id) {
        Webinar webinar = webinarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Webinar not found"));

        if (webinar.getCurrentParticipants() < webinar.getMaxParticipants()) {
            webinar.setCurrentParticipants(webinar.getCurrentParticipants() + 1);
            return webinarRepository.save(webinar);
        } else {
            throw new RuntimeException("Webinar is full");
        }
    }

}
