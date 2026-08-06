# Event Ticket Booking System - Performance Benchmark

## Environment

- Application: Spring Boot 3.5
- Java: 21
- Database: PostgreSQL 16
- Authentication: JWT
- ORM: Hibernate/JPA
- Benchmark Tool: k6 v2.0.0
- Machine: Local Development Environment
- Duration per Test: 30 seconds

---

# Endpoint Tested

GET /events

Authorization:
Bearer JWT

Purpose:
Measure authenticated database read performance.

---

# Results

| Concurrent Virtual Users | Average Latency | P95 Latency | Throughput | Success Rate |
|--------------------------|----------------|-------------|------------|--------------|
| 10 | 12.16 ms | 29.40 ms | 729 req/s | 100% |
| 50 | 60.40 ms | 150.26 ms | 767 req/s | 100% |
| 100 | 112.38 ms | 312.78 ms | 774 req/s | 100% |

---

# Login Benchmark

Endpoint:

POST /login

Authentication:
Email + BCrypt + JWT Generation

Results

Average Latency: 299.49 ms

P95 Latency: 402.75 ms

Throughput: 33.17 req/s

Success Rate: 100%

---

# Observations

- Throughput remained stable around 770 requests/sec.
- Latency increased as concurrency increased.
- No failed HTTP requests were observed.
- Login latency is significantly higher because BCrypt password verification is intentionally computationally expensive.

---

# Methodology

The benchmark was executed using k6 with authenticated requests.

Each benchmark ran for 30 seconds.

Measurements collected:

- Average latency
- Median latency
- P95 latency
- Throughput
- Success rate
- Failure rate