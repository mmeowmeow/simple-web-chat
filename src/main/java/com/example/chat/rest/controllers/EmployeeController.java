package com.example.chat.rest.controllers;

import com.example.chat.rest.models.Employee;
import com.example.chat.rest.models.Incident;
import com.example.chat.rest.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    Iterable<Employee> getAllEmployees() {
        return employeeService.get();
    }

    @GetMapping("/{employeeId}")
    Employee getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.get(employeeId);
    }

    @PostMapping("/{employeeId}/incidents")
    @ResponseStatus(HttpStatus.CREATED)
    void addIncident(@PathVariable Long employeeId, @RequestBody Incident incident) {
        this.employeeService.add(employeeId, incident);
    }
}
