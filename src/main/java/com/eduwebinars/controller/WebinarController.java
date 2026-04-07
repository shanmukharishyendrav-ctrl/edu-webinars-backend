package com.eduwebinars.controller;

import com.eduwebinars.entity.Webinar;
import com.eduwebinars.service.WebinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webinars")
@CrossOrigin(origins = "*")
public class WebinarController {

    @Autowired
    private WebinarService webinarService;

    @GetMapping
    public ResponseEntity<List<Webinar>> getAllWebinars() {
        List<Webinar> webinars = webinarService.getAllWebinars();
        return ResponseEntity.ok(webinars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webinar> getWebinarById(@PathVariable Long id) {
        return webinarService.getWebinarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Webinar> createWebinar(@RequestBody Webinar webinar) {
        Webinar createdWebinar = webinarService.createWebinar(webinar);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWebinar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Webinar> updateWebinar(@PathVariable Long id, @RequestBody Webinar webinar) {
        try {
            Webinar updatedWebinar = webinarService.updateWebinar(id, webinar);
            return ResponseEntity.ok(updatedWebinar);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebinar(@PathVariable Long id) {
        webinarService.deleteWebinar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Webinar>> getByCategory(@PathVariable String category) {
        List<Webinar> webinars = webinarService.findByCategory(category);
        return ResponseEntity.ok(webinars);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Webinar>> searchByTitle(@RequestParam String title) {
        List<Webinar> webinars = webinarService.findByTitle(title);
        return ResponseEntity.ok(webinars);
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<Webinar> registerParticipant(@PathVariable Long id) {
        try {
            Webinar webinar = webinarService.registerParticipant(id);
            return ResponseEntity.ok(webinar);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
