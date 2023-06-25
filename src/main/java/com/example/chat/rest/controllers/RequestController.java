package com.example.chat.rest.controllers;

import com.example.chat.rest.models.Request;
import com.example.chat.rest.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users/{userId}/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addRequest(@PathVariable Long userId, @RequestBody Request request) {
        request.setDateOfContact(LocalDate.now());
        this.requestService.add(userId, request);
    }

    @PutMapping("/{requestId}")
    void updateRequest(@PathVariable Long userId, @PathVariable Long requestId, @RequestBody Request request) {
        request.setId(requestId);
        request.setDateOfContact(LocalDate.now());
        this.requestService.update(userId, request);
    }

    @DeleteMapping("/{requestId}")
    void deleteRequest(@PathVariable Long userId, @PathVariable Long requestId) {
        this.requestService.delete(userId, requestId);
    }
}
