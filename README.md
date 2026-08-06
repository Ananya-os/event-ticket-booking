# 🎟️ Event Ticket Booking System

A secure, production-oriented backend application for managing events and ticket bookings, built using **Spring Boot 3.5**, **Java 21**, **PostgreSQL**, **Spring Security**, and **JWT Authentication**.

The project follows a layered architecture (Controller → Service → Repository), implements role-based access control, transactional booking logic, database versioning with Flyway, and RESTful APIs documented using Swagger/OpenAPI.

---

## Key Highlights

- JWT-based stateless authentication
- Role-Based Access Control (ADMIN / USER)
- Secure password storage using BCrypt
- Event creation and ticket booking
- Transactional seat allocation
- Optimistic locking for concurrent updates
- PostgreSQL with Flyway migrations
- Global exception handling
- Bean Validation
- Swagger/OpenAPI documentation
- Pageable REST APIs
- k6 performance benchmarking

---

## Tech Stack

| Category | Technology |
|----------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Security | Spring Security, JWT |
| Database | PostgreSQL 16 |
| ORM | Hibernate / Spring Data JPA |
| Migration | Flyway |
| Build Tool | Maven |
| API Testing | Postman |
| API Documentation | Swagger / OpenAPI |
| Performance Testing | k6 |

---

# Architecture

The application follows a layered architecture to ensure clear separation of concerns and maintainability.

```text
                Client (Postman / React)

                        │
                        ▼

             Spring Security + JWT Filter

                        │
                        ▼

                  REST Controllers

                        │
                        ▼

                     Services

        (Business Logic & Transactions)

                        │
                        ▼

                 Spring Data JPA

                  Repository Layer

                        │
                        ▼

                  PostgreSQL Database
```

## Layer Responsibilities

### Controller Layer

- Exposes REST APIs
- Validates incoming requests
- Delegates business logic to services
- Returns DTO responses

### Service Layer

- Implements business logic
- Handles transactions
- Performs authorization checks
- Maps entities to DTOs

### Repository Layer

- Uses Spring Data JPA
- Performs database operations
- Supports pagination and sorting

### Database Layer

- PostgreSQL
- Flyway versioned migrations
- Hibernate ORM
- Optimistic locking using `@Version`

---

# Features

## Authentication & Security

- JWT-based stateless authentication
- BCrypt password hashing
- Role-Based Access Control (ADMIN / USER)
- Protected REST APIs
- Custom authentication and authorization error responses

## Event Management

- Create events (ADMIN)
- Retrieve paginated event listings
- Optimistic locking for concurrent updates

## Ticket Booking

- Book tickets for available events
- Automatic seat availability updates
- Transactional booking workflow
- Booking history retrieval

## API Design

- RESTful endpoints
- Request and response DTOs
- Bean Validation
- Centralized exception handling
- Standardized JSON error responses

## Database

- PostgreSQL
- Flyway schema migrations
- Hibernate/JPA
- Entity relationships

---

# Performance Benchmarks

Performance testing was performed using **k6** against authenticated REST endpoints running on a local development environment.

## Test Environment

- Spring Boot 3.5
- Java 21
- PostgreSQL 16
- JWT Authentication
- k6 v2.0.0
- Test Duration: 30 seconds

### GET /events Benchmark

| Concurrent Virtual Users | Average Latency | P95 Latency | Throughput | Success Rate |
|--------------------------:|---------------:|------------:|-----------:|-------------:|
| 10 | 12.16 ms | 29.40 ms | 729 req/s | 100% |
| 50 | 60.40 ms | 150.26 ms | 767 req/s | 100% |
| 100 | 112.38 ms | 312.78 ms | 774 req/s | 100% |

### POST /login Benchmark

| Metric | Value |
|--------|------:|
| Average Latency | 299.49 ms |
| P95 Latency | 402.75 ms |
| Throughput | 33.17 req/s |
| Success Rate | 100% |

### Key Observations

- Successfully benchmarked authenticated APIs using **k6**.
- Sustained approximately **774 requests/sec** on the `GET /events` endpoint with **100 concurrent virtual users**.
- Maintained **100% successful responses** with **0 failed requests** during testing.
- Login requests exhibit higher latency due to BCrypt password verification and JWT generation.

---

# REST API

## Authentication

| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/login` | Public |

---

## Users

| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/users` | Public (Registration) |
| GET | `/users` | ADMIN |

---

## Events

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/events` | Authenticated |
| POST | `/events` | ADMIN |

---

## Bookings

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/bookings` | Authenticated |
| POST | `/bookings` | Authenticated |

---

# Security

The application implements stateless authentication and role-based authorization using Spring Security and JWT.

## Authentication

- JWT-based stateless authentication
- BCrypt password hashing
- Custom JWT authentication filter
- Custom `UserDetailsService`

## Authorization

| Role | Permissions |
|------|-------------|
| USER | View events, create bookings, view own bookings |
| ADMIN | Manage events, view all users, view all bookings |

## Security Features

- Passwords stored as BCrypt hashes
- Role-Based Access Control (RBAC)
- Unauthorized requests return **401 Unauthorized**
- Forbidden requests return **403 Forbidden**
- Standardized JSON error responses

---

# Database Design

## Entities

### User

- id
- name
- email
- password (BCrypt hashed)
- role

### Event

- id
- name
- venue
- dateTime
- totalSeats
- availableSeats
- version (`@Version`)

### Booking

- id
- bookingTime
- numberOfSeats
- status
- user
- event

## Relationships

- One User → Many Bookings
- One Event → Many Bookings

## Database Features

- PostgreSQL
- Flyway schema migrations
- Foreign key relationships
- Optimistic locking for concurrent updates

---

# Project Structure

```text
src
└── main
    ├── java
    │   └── com.ananya.event_ticket_booking
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── exception
    │       ├── repository
    │       ├── security
    │       ├── service
    │       └── EventTicketBookingApplication.java
    │
    └── resources
        ├── db
        │   └── migration
        └── application.properties
```

---

# Running the Project

## Prerequisites

- Java 21
- Maven
- PostgreSQL 16

## Configure Database

Set the following environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

## Run

```bash
mvn spring-boot:run
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

# Future Improvements

- React frontend
- Docker and Docker Compose support
- GitHub Actions CI pipeline
- Unit and integration test suite
- Redis caching
- Email notifications
- Monitoring with Spring Boot Actuator
- Container deployment