package com.example.chat.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;

public class Request {
    @Id
    private Long id;
    private String message;
    @Column("DATE_OF_CONTACT")
    private LocalDate dateOfContact;

    @MappedCollection(idColumn = "REQUESTID")
    private Incident incident;

    public Request(Long id, String message, LocalDate dateOfContact, Incident incident) {
        this.id = id;
        this.message = message;
        this.dateOfContact = dateOfContact;
        this.incident = incident;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateOfContact() {
        return dateOfContact;
    }

    public void setDateOfContact(LocalDate dateOfContact) {
        this.dateOfContact = dateOfContact;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
