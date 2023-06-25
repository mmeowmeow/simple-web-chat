package com.example.chat.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

public class Incident {
    @Id
    private Long id;
    private String solution;
    @Column("DATE_OF_SOLUTION")
    private LocalDate dateOfSolution;
    private Integer rating;

    public Incident(Long id, String solution, LocalDate dateOfSolution, Integer rating) {
        this.id = id;
        this.solution = solution;
        this.dateOfSolution = dateOfSolution;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public LocalDate getDateOfSolution() {
        return dateOfSolution;
    }

    public void setDateOfSolution(LocalDate dateOfSolution) {
        this.dateOfSolution = dateOfSolution;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
