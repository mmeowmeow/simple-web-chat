package com.example.chat.rest.services;

import com.example.chat.rest.models.Request;
import com.example.chat.rest.repositories.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class RequestService {
    private final UserRepository userRepository;

    public RequestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(Long userId, Request request) {
        this.userRepository.findById(userId)
                .map(user -> {
                    Set<Request> requests = user.getRequests();
                    requests.add(request);
                    this.userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " is not found"));
    }

    public void update(@NonNull Long userId, Request request) {
        this.userRepository.findById(userId)
                .map(user -> {
                    Set<Request> requests = user.getRequests();
                    Request existingRequest = requests.stream()
                            .filter(request1 -> Objects.equals(request.getId(), request1.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Request with ID " + request.getId() + " is not found"));

                    existingRequest.setMessage(request.getMessage());
                    existingRequest.setDateOfContact(request.getDateOfContact());

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

                    requests.remove(existingRequest);

                    this.userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " is not found"));
    }

}
