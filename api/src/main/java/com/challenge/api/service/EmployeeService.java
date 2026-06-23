package com.challenge.api.service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.dto.EmployeeResponse;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeByUuid(UUID uuid);

    EmployeeResponse createEmployee(CreateEmployeeRequest request);
}
