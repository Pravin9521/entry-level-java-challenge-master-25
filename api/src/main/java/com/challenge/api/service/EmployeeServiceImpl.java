package com.challenge.api.service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.dto.EmployeeResponse;
import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.repository.EmployeeRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getEmployeeByUuid(UUID uuid) {
        Employee employee = employeeRepository.findById(uuid).orElseThrow(() -> new EmployeeNotFoundException(uuid));

        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {

        Employee employee = new EmployeeImpl();

        employee.setUuid(UUID.randomUUID());

        String firstName = request.getFirstName().trim();
        String lastName = request.getLastName().trim();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        employee.setFullName(firstName + " " + lastName);

        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        String email =
                request.getEmail() == null ? null : request.getEmail().trim().toLowerCase();
        if (email != null && employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee with email " + email + " already exists");
        }
        employee.setEmail(email);

        employee.setContractHireDate(Instant.now());

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setUuid(employee.getUuid());
        response.setFullName(employee.getFullName());
        response.setSalary(employee.getSalary());
        response.setAge(employee.getAge());
        response.setJobTitle(employee.getJobTitle());
        response.setEmail(employee.getEmail());
        response.setContractHireDate(employee.getContractHireDate());

        return response;
    }
}
