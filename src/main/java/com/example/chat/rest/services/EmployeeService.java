package com.example.chat.rest.services;

import com.example.chat.rest.models.Employee;
import com.example.chat.rest.models.Incident;
import com.example.chat.rest.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> get() {
        return employeeRepository.findAll();
    }

    public Employee get(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Employee with ID " + employeeId + " is not found"));
    }

    public void add(Long employeeId, Incident incident) {
        this.employeeRepository.findById(employeeId)
                .map(employee -> {
                    Set<Incident> incidents = employee.getIncidents();
                    incidents.add(incident);
                    this.employeeRepository.save(employee);
                    return employee;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + employeeId + " is not found"));
    }

}
