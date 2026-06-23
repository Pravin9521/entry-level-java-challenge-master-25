package com.challenge.api.repository;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    public InMemoryEmployeeRepository() {

        Employee employee = new EmployeeImpl();

        UUID uuid = UUID.randomUUID();

        employee.setUuid(uuid);
        employee.setFirstName("Rocky");
        employee.setLastName("Singh");
        employee.setFullName("Rocky Singh");
        employee.setAge(25);
        employee.setSalary(100000);
        employee.setEmail("rocky.singh@xyz.com");
        employee.setJobTitle("Software Engineer");
        employee.setContractHireDate(Instant.now());

        employees.put(uuid, employee);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Optional<Employee> findById(UUID uuid) {
        return Optional.ofNullable(employees.get(uuid));
    }

    @Override
    public Employee save(Employee employee) {
        employees.put(employee.getUuid(), employee);

        return employee;
    }
}
