package com.example.chat.rest.services;

import com.example.chat.rest.models.Incident;
import com.example.chat.rest.models.Request;
import com.example.chat.rest.repositories.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class IncidentService {
    private final UserRepository userRepository;

    public IncidentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(Long userId, Long requestId, Incident incident) {
        this.userRepository.findById(userId)
                .map(user -> {
                    Set<Request> requests = user.getRequests();
                    Request existingRequest = requests.stream()
                            .filter(request -> Objects.equals(requestId, request.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Request with ID " + requestId + " is not found"));

                    if (incident.getRating() == null) {
                        incident.setRating(10);
                    }
                    existingRequest.setIncident(incident);

                    this.userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " is not found"));
    }

    public void update(@NonNull Long userId, Long requestId, Incident incident) {
        this.userRepository.findById(userId)
                .map(user -> {
                    Set<Request> requests = user.getRequests();
                    Request existingRequest = requests.stream()
                            .filter(request -> Objects.equals(requestId, request.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Request with ID " + requestId + " is not found"));

                    Incident existingIncident = Optional.ofNullable(existingRequest.getIncident()).orElseThrow(() -> new NullPointerException("Request with ID " + requestId + " has no incident"));
                    existingIncident.setDateOfSolution(incident.getDateOfSolution());
                    existingIncident.setSolution(incident.getSolution());
                    if (incident.getRating() != null) {
                        existingIncident.setRating(incident.getRating());
                    }

                    this.userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " is not found"));
    }

    public void delete(Long userId, Long requestId) {
        this.userRepository.findById(userId)
                .map(user -> {
                    Set<Request> requests = user.getRequests();
                    Request existingRequest = requests.stream()
                            .filter(request -> Objects.equals(requestId, request.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Request with ID " + requestId + " is not found"));

                    Incident existingIncident = Optional.ofNullable(existingRequest.getIncident()).orElseThrow(() -> new NullPointerException("Request with ID " + requestId + " has no incident"));
                    existingRequest.setIncident(null);

                    this.userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " is not found"));
    }
}
