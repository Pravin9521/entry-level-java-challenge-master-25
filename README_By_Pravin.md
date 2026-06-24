# Employee Management REST API

A secure Employee Management REST API built using Spring Boot.

## Overview

This application exposes employee information through secure REST endpoints. The implementation follows a layered architecture (Controller → Service → Repository) and includes validation, exception handling, and role-based access control.

## Features

* Create Employee
* Get Employee by UUID
* Get All Employees
* Request Validation
* Global Exception Handling
* Role-Based Access Control (RBAC)
* Email Uniqueness Validation
* Basic Authentication using Spring Security

## Authentication

All endpoints require authentication.

### Admin User

* Username: `admin`
* Password: `admin123`

### Standard User

* Username: `user`
* Password: `user123`

## Authorization

| Endpoint                    | Access      |
| --------------------------- | ----------- |
| GET /api/v1/employee        | USER, ADMIN |
| GET /api/v1/employee/{uuid} | USER, ADMIN |
| POST /api/v1/employee       | ADMIN only  |

## Error Responses

| Status Code | Description                            |
| ----------- | -------------------------------------- |
| 400         | Validation error / Invalid UUID format |
| 401         | Unauthorized                           |
| 403         | Forbidden                              |
| 404         | Employee not found                     |
| 409         | Duplicate email                        |
| 500         | Internal server error                  |

## Implementation Notes

* In-memory repository used for employee storage.
* DTOs are used to separate API contracts from domain models.
* Email uniqueness validation is enforced before employee creation.
* Global exception handling is implemented using `@RestControllerAdvice`.
* Role-Based Access Control is implemented using Spring Security.
* Employee UUIDs are generated automatically during creation.

## Sample Request

POST `/api/v1/employee`

```json
{
  "firstName": "Lucy",
  "lastName": "Grey",
  "age": 30,
  "salary": 75000,
  "jobTitle": "Software Engineer",
  "email": "lucy.grey@example.com"
}
```
