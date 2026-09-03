# Sunrise Dental Clinic Management System

## Project overview

A secure three-tier web application for Sunrise Dental Clinic in Colombo, created for the **CIS6003 Advanced Programming Assignment**. It manages staff access, patients, dentists, treatments, appointments, billing, reports, and printable receipts, with a versioned JSON API for distributed use.

## Features

- BCrypt staff authentication, ADMIN/STAFF roles, CSRF-protected forms, secure logout, and access-denied handling
- Dashboard totals for appointments, patients, dentists, and daily revenue
- Patient registration, editing, viewing, validation, and name/contact search
- Dentist and treatment administration with activate/deactivate controls
- Appointment number generation, rescheduling, status changes, past-date rejection, and active-dentist time-slot protection
- BigDecimal billing with a configurable consultation fee, duplicate prevention, unique bill numbers, and print CSS
- Date-range, dentist, status, and revenue reporting
- Responsive blue/white Thymeleaf interface and staff help page
- Validated DTO-based REST services with centralized safe JSON errors

## Technology stack

Java 21, Spring Boot 4.1.1, Spring MVC, Spring Security, Spring Data JPA/Hibernate, Thymeleaf, MySQL 8, H2 (tests), Maven, JUnit 5, Mockito, MockMvc, and GitHub Actions.

## Architecture and patterns

The presentation tier contains MVC/REST controllers, DTOs, and Thymeleaf. The business tier contains transactional services and clinic rules. The data-access tier uses JPA entities and Spring Data repositories. The application demonstrates MVC, Service, Repository, dependency injection, DTO/mapping, and centralized exception-handling patterns.

## Database requirements

Create (do not drop) a MySQL database named `sunrise_dental_clinic` and grant an application user access. Local development uses Hibernate `update`; automated tests use an isolated H2 database in MySQL compatibility mode.

Required environment variables:

| Variable | Purpose |
|---|---|
| `DB_USERNAME` | MySQL application username |
| `DB_PASSWORD` | MySQL application password |
| `APP_ADMIN_USERNAME` | Initial admin username (optional after first seed) |
| `APP_ADMIN_PASSWORD` | Initial admin password (optional after first seed) |
| `CONSULTATION_FEE` | Consultation fee; defaults to `1000.00` |

Never commit passwords or paste them into configuration, source files, logs, screenshots, or issue reports. The admin bootstrap hashes the password with BCrypt, creates the user only when absent, and never logs it.

## Run on Windows

Start MySQL and ensure the database/user exist. In PowerShell, set the variables only for the current terminal, then run:

```powershell
$env:DB_USERNAME = "sunrise_app"
$env:DB_PASSWORD = Read-Host "Database password" -MaskInput
$env:APP_ADMIN_USERNAME = "admin"
$env:APP_ADMIN_PASSWORD = Read-Host "Initial admin password" -MaskInput
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080`. After the first successful seed, the two admin variables may be omitted. To run tests:

```powershell
.\mvnw.cmd -Dspring.profiles.active=test test
```

## REST endpoint summary

All endpoints require the same authenticated staff access as the web interface and keep CSRF protection enabled.

| Method | Path | Purpose |
|---|---|---|
| GET/POST | `/api/v1/patients` | Search/list or create patients |
| GET | `/api/v1/patients/{id}` | Read one patient |
| GET/POST | `/api/v1/appointments` | List or create appointments |
| GET | `/api/v1/appointments/{number}` | Search by appointment number |
| POST | `/api/v1/bills?appointmentNumber=...` | Generate a bill |
| GET | `/api/v1/bills/{id}` | Read a bill |
| GET | `/api/v1/summary` | Dashboard summary JSON |

## Project structure

```text
src/main/java/com/sunrise/dentalclinic/
├── bootstrap/       # Environment-driven administrator seed
├── config/          # Security configuration
├── controller/      # MVC and api/ REST controllers
├── dto/             # Validated requests and safe responses
├── exception/       # Business errors and central handler
├── model/           # JPA entities and enums
├── repository/      # Spring Data access layer
└── service/         # Transactional business layer
src/main/resources/templates/ and static/css/
src/test/            # Unit, repository, integration, security, and API tests
docs/                # Requirements, test plan, and UML
```

## Continuous integration

`.github/workflows/maven.yml` runs tests and packages with Java 21 on every push and pull request. CI uses only the H2 test profile and requires no local MySQL credentials.

## UML documentation

The original PlantUML and rendered UML assets are preserved under `docs/uml/`, including the class, use-case, login, appointment-registration, and bill-generation diagrams.
