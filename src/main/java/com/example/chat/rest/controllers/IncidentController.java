package com.example.chat.rest.controllers;

import com.example.chat.rest.models.Incident;
import com.example.chat.rest.services.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users/{userId}/requests/{requestId}/incident")
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addIncident(@PathVariable Long userId, @PathVariable Long requestId, @RequestBody Incident incident) {
        incident.setDateOfSolution(LocalDate.now());
        this.incidentService.add(userId, requestId, incident);
    }

    @PutMapping
    void updateIncident(@PathVariable Long userId, @PathVariable Long requestId, @RequestBody Incident incident) {
        incident.setDateOfSolution(LocalDate.now());
        this.incidentService.update(userId, requestId, incident);
    }

    @DeleteMapping
    void deleteIncident(@PathVariable Long userId, @PathVariable Long requestId) {
        this.incidentService.delete(userId, requestId);
    }
}
