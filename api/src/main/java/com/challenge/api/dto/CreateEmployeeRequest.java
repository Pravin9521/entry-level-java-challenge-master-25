package com.challenge.api.dto;

import jakarta.validation.constraints.*;

public class CreateEmployeeRequest {
    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String lastName;

    @Positive @Max(10000000)
    private Integer salary;

    @Min(18)
    @Max(100)
    private Integer age;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 100)
    private String jobTitle;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
