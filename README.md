# OpsCore

**A Spring Boot REST API for incident, task, and user management.**

OpsCore is a backend application developed with Java and Spring Boot to manage users, incidents, and tasks through a RESTful API.

The project is being developed as a hands-on backend project with a focus on layered architecture, separation of responsibilities, database integration, API design, and testing.

> 🚧 **Status: In Progress** — core CRUD operations and the Service layer have been implemented. Further improvements such as validation, global exception handling, authentication, and broader test coverage are planned.

---

## Overview

OpsCore currently provides three main resources:

- **Users** — create, retrieve, update, and delete users.
- **Incidents** — create, retrieve, update, and delete incidents.
- **Tasks** — create, retrieve, update, and delete tasks.

The application uses a layered backend architecture where HTTP requests are handled by controllers, business operations are managed by service classes, and database access is handled through Spring Data JPA repositories.

---

## Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL
```

### Project Structure
<img width="5187" height="6168" alt="diagram" src="https://github.com/user-attachments/assets/d4b00aeb-3323-4712-a441-2f69d9889e5c" />


```text
src
├── main
│   ├── java
│   │   └── com.opscore
│   │       ├── controller
│   │       ├── dto
│   │       ├── exception
│   │       ├── model
│   │       ├── repository
│   │       └── service
│   │
│   └── resources
│       └── application.properties
│
└── test
    └── java
        └── com.opscore
            └── service
```

### Layers

- **`controller`** — Defines REST API endpoints and handles HTTP requests and responses.
- **`service`** — Contains application logic and coordinates operations between controllers and repositories.
- **`repository`** — Provides database access through Spring Data JPA.
- **`model`** — Contains JPA entity classes representing `User`, `Incident`, and `Task`.
- **`dto`** — Defines request data structures used when creating and updating resources.
- **`exception`** — Contains custom exceptions for cases such as missing resources and duplicate user emails.

---

## Tech Stack

| Category             | Technologies               |
| -------------------- | -------------------------- |
| Language             | Java 21                    |
| Framework            | Spring Boot 3.5.14         |
| Persistence          | Spring Data JPA, Hibernate |
| Database             | PostgreSQL                 |
| Database Environment | Docker                     |
| Build Tool           | Maven                      |
| API Testing          | Postman                    |
| Testing              | JUnit 5, Mockito           |
| Version Control      | Git, GitHub                |

---

## Current Features

### User Management

- Create users
- Retrieve all users
- Retrieve a user by ID
- Update users
- Delete users
- Prevent duplicate email registration
- Return `404 Not Found` when a requested user does not exist
- Return `409 Conflict` when an email is already registered

### Incident Management

- Create incidents
- Retrieve all incidents
- Retrieve an incident by ID
- Update incidents
- Delete incidents
- Return `404 Not Found` when an incident does not exist

### Task Management

- Create tasks
- Retrieve all tasks
- Retrieve a task by ID
- Update tasks
- Delete tasks
- Return `404 Not Found` when a task does not exist

---

## REST API

### Users

| Method   | Endpoint              | Description           |
| -------- | --------------------- | --------------------- |
| `GET`    | `/api/users`          | Retrieve all users    |
| `GET`    | `/api/users/{userID}` | Retrieve a user by ID |
| `PUT`    | `/api/users/{userID}` | Update a user         |
| `DELETE` | `/api/users/{userID}` | Delete a user         |
| `POST`   | `/api/users`          | Create a user         |

### Tasks

| Method   | Endpoint              | Description           |
| -------- | --------------------- | --------------------- |
| `GET`    | `/api/tasks`          | Retrieve all tasks    |
| `GET`    | `/api/tasks/{taskID}` | Retrieve a task by ID |
| `POST`   | `/api/tasks`          | Create a task         |
| `PUT`    | `/api/tasks/{taskID}` | Update a task         |
| `DELETE` | `/api/tasks/{taskID}` | Delete a task         |

### Incidents

| Method   | Endpoint                      | Description                |
| -------- | ----------------------------- | -------------------------- |
| `GET`    | `/api/incidents`              | Retrieve all incidents     |
| `GET`    | `/api/incidents/{incidentID}` | Retrieve an incident by ID |
| `POST`   | `/api/incidents`              | Create an incident         |
| `PUT`    | `/api/incidents/{incidentID}` | Update an incident         |
| `DELETE` | `/api/incidents/{incidentID}` | Delete an incident         |

---

## Data Models

### User

```text
User
├── userID
├── userName
└── email
```

### Incident

```text
Incident
├── incidentID
├── title
├── description
└── priority
```

### Task

```text
Task
├── taskID
├── taskName
├── taskDescription
└── taskStatus
```

At the current stage, `User`, `Incident`, and `Task` are separate entities. Relationships between these entities have not yet been implemented.

---

## Database

OpsCore uses **PostgreSQL** as its relational database.

PostgreSQL is currently run in a Docker container:

```text
opscore-db
    │
    └── PostgreSQL :5432
```

The Spring Boot application connects to the database through Spring Data JPA and Hibernate.

The database schema is currently managed using:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

## Testing

API endpoints have been manually tested using **Postman**, including CRUD operations and error scenarios.

The project also includes the Spring Boot testing stack with **JUnit 5 and Mockito**. Initial unit testing infrastructure has been established for the service layer, with broader unit test coverage planned as the project develops.

---

## Error Handling

Custom exceptions are currently implemented for:

- User not found
- Task not found
- Incident not found
- Duplicate user email

The application uses appropriate HTTP status codes for these cases, including:

```text
404 Not Found
409 Conflict
204 No Content
201 Created
200 OK
```

A centralized global exception handler is planned for a future stage.

---

## Getting Started

### Prerequisites

- Java 21
- Docker
- Git

Maven does not need to be installed separately because the project includes the Maven Wrapper.

### 1. Clone the repository

```bash
git clone https://github.com/israfezaaglamis/opscore.git
cd opscore
```

### 2. Start PostgreSQL

Create and run a PostgreSQL container:

```bash
docker run --name opscore-db -e POSTGRES_DB=opscore -e POSTGRES_PASSWORD=YOUR_PASSWORD -p 5432:5432 -d postgres
```

Replace `YOUR_PASSWORD` with the PostgreSQL password configured for your local environment.

### 3. Configure the database connection

Update the database credentials in:

```text
src/main/resources/application.properties
```

Do not commit real database credentials to the repository.

### 4. Run the application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

The API runs by default on:

```text
http://localhost:8080
```

---

## Running Tests

Windows:

```bash
mvnw.cmd test
```

Linux/macOS:

```bash
./mvnw test
```

---

## Development Roadmap

The project is being developed incrementally. Planned improvements include:

- Request validation with Bean Validation
- Centralized global exception handling
- More comprehensive JUnit 5 and Mockito unit tests
- Integration testing
- Relationships between Users, Incidents, and Tasks
- Swagger / OpenAPI API documentation
- Authentication and authorization
- Improved API response models
- Docker Compose for application and database orchestration
- CI/CD pipeline

These features are planned and are not part of the current implementation.

---

## Project Status

### Completed

- [x] Spring Boot project setup
- [x] Layered architecture
- [x] User CRUD
- [x] Task CRUD
- [x] Incident CRUD
- [x] DTO-based request handling
- [x] Spring Data JPA repositories
- [x] PostgreSQL integration
- [x] PostgreSQL running with Docker
- [x] Custom exceptions
- [x] HTTP status handling
- [x] Postman API testing
- [x] Initial JUnit 5 / Mockito test setup
- [x] Git repository and GitHub integration

### Planned

- [ ] Bean Validation
- [ ] Global exception handler
- [ ] Comprehensive unit tests
- [ ] Integration tests
- [ ] Entity relationships
- [ ] Swagger / OpenAPI
- [ ] Authentication & Authorization
- [ ] Docker Compose
- [ ] CI/CD

---

## Author

**İsra Feza Ağlamış**

[LinkedIn](https://www.linkedin.com/in/israfezaaglamis) · [GitHub](https://github.com/israfezaaglamis)
