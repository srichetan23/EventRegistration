# Event Registration System

[![CI](https://github.com/srichetan23/EventRegistration/actions/workflows/ci.yml/badge.svg)](https://github.com/srichetan23/EventRegistration/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

A full-stack **event registration platform** built with Spring Boot. Users can sign up, browse a catalogue of events, and register while seats last; an admin gets a live analytics dashboard of every registration.

> Replace the badges' username/repo if you fork this, and drop real screenshots into the `docs/` folder (see [Screenshots](#screenshots)).

## Features

- **Authentication** — user registration and login secured with Spring Security and BCrypt-hashed passwords.
- **Event catalogue** — six events, each with a date, venue, description, and limited capacity.
- **Smart registration** — one-click register, "seats left" counter, automatic **Event Full** state, and duplicate-registration prevention.
- **My Registrations** — users view and **cancel** their registrations.
- **Admin dashboard** — a single admin sees totals (users, registrations, events) and a **Chart.js** bar chart of registrations per event, plus a full registration table.
- **Two database modes** — MySQL by default, with a one-flag **H2 in-memory** profile for quick runs and tests.

## Tech Stack

| Layer        | Technology                                  |
|--------------|---------------------------------------------|
| Language     | Java 17                                      |
| Framework    | Spring Boot 3.5 (Web, Data JPA, Security, Validation) |
| Views        | Thymeleaf + Bootstrap 5                       |
| Charts       | Chart.js                                      |
| Database     | MySQL 8 (prod), H2 (test/dev)                 |
| Build / CI   | Maven, Docker, GitHub Actions                 |

## Getting Started

### Option A — Run locally (MySQL)

Requires JDK 17 and a running MySQL. The `springbootproject` database is created automatically.

```bash
./mvnw spring-boot:run
```

App: http://localhost:7478

Override DB credentials without editing code:

```bash
DB_URL=jdbc:mysql://localhost:3306/springbootproject DB_USERNAME=root DB_PASSWORD=secret ./mvnw spring-boot:run
```

### Option B — Run locally (no MySQL needed)

Uses an in-memory H2 database (data resets on restart):

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

### Option C — Docker (app + MySQL, one command)

Requires Docker. Builds the app and starts MySQL alongside it:

```bash
docker compose up --build
```

App: http://localhost:7478

## Usage

1. Open http://localhost:7478 and **Register** a user, then **Login**.
2. Browse events and click **Register** — watch the seats-left counter update.
3. Visit **My Registrations** to cancel.
4. From the home page choose **Admin** and log in to view the dashboard.

**Default admin:** `Srichetan` / `Srichetan` (seeded on first run).

## Running Tests

```bash
./mvnw test
```

Tests run against H2, so no database setup is needed. The same command runs automatically on every push via GitHub Actions.

## Screenshots

Add images to a `docs/` folder and reference them here, for example:

```
![Events page](docs/events.png)
![Admin dashboard](docs/admin-dashboard.png)
```

## Project Structure

```
src/main/java/com/bmt/MyStore
├── config/          Security + data seeding
├── controller/      Web controllers (account, events, admin, home)
├── models/          JPA entities + form DTO
├── repositories/    Spring Data JPA repositories
└── services/        UserDetailsService for login
src/main/resources/templates   Thymeleaf pages
```

## License

Released under the MIT License.
