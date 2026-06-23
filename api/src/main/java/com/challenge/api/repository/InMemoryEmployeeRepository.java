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

        addEmployee("Rocky", "Singh", 25, 100000, "rocky.singh@xyz.com", "Software Engineer");

        addEmployee("Robin", "Green", 22, 120000, "robin.green@xyz.com", "Associate Software Engineer");

        addEmployee("Rahul", "Sharma", 28, 150000, "rahul.sharma@xyz.com", "Senior Software Engineer");

        addEmployee("Green", "Arrow", 30, 180000, "green.arrow@xyz.com", "Tech Lead");

        addEmployee("Amit", "Verma", 35, 250000, "amit.verma@xyz.com", "Engineering Manager");

        addEmployee("Sneha", "Joshi", 27, 140000, "sneha.joshi@xyz.com", "Backend Developer");

        addEmployee("Vikram", "Rathod", 24, 110000, "vikram.rathod@xyz.com", "Associate Software Engineer");

        addEmployee("Pooja", "Deshmukh", 29, 160000, "pooja.deshmukh@xyz.com", "Frontend Developer");
    }

    private void addEmployee(String firstName, String lastName, int age, int salary, String email, String jobTitle) {

        Employee employee = new EmployeeImpl();

        UUID uuid = UUID.randomUUID();

        employee.setUuid(uuid);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setFullName(firstName + " " + lastName);
        employee.setAge(age);
        employee.setSalary(salary);
        employee.setEmail(email);
        employee.setJobTitle(jobTitle);
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
