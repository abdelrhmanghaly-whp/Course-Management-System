# Course Management System

A RESTful API built with Spring Boot for managing educational topics and courses with JWT authentication.

## About

This project demonstrates a complete backend REST API with authentication, validation, pagination, and search functionality. Courses are organized under topics, with each course belonging to a specific topic.

## Features

- **JWT Authentication** - Secure login and registration with token-based auth
- **Input Validation** - Request validation using Bean Validation
- **Pagination** - Paginated responses for list endpoints
- **Search & Filter** - Search courses by name or description
- **Exception Handling** - Global error handling with consistent responses
- **HTTP Status Codes** - Proper REST status codes (201, 204, 404, 400, 401)

## Tech Stack

- Spring Boot 3.5.7
- Spring Security + JWT
- Spring Data JPA
- H2 Database (in-memory)
- Java 21
- Maven

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven

### Run the Application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Documentation

### Authentication Endpoints (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive JWT token |

### Topic Endpoints (Protected)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/topics` | Get all topics (paginated) |
| GET | `/topics/{id}` | Get specific topic |
| POST | `/topics` | Create new topic |
| PUT | `/topics/{id}` | Update topic |
| DELETE | `/topics/{id}` | Delete topic |

### Course Endpoints (Protected)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/topics/{topicId}/courses` | Get all courses for a topic (paginated) |
| GET | `/topics/{topicId}/courses/{courseId}` | Get specific course |
| POST | `/topics/{topicId}/courses` | Create new course |
| PUT | `/topics/{topicId}/courses/{id}` | Update course |
| DELETE | `/topics/{topicId}/courses/{id}` | Delete course |
| GET | `/courses/search?keyword={keyword}` | Search courses by name or description |

## Usage Examples

### 1. Register a User

```bash
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "username": "gholyo",
  "password": "password123",
  "email": "gholyo@email.com"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "gholyo"
}
```

### 2. Login

```bash
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "gholyo",
  "password": "password123"
}
```

### 3. Access Protected Endpoints

All other endpoints require JWT token in the Authorization header:

```bash
GET http://localhost:8080/topics
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### 4. Create a Topic

```bash
POST http://localhost:8080/topics
Authorization: Bearer {your-token}
Content-Type: application/json

{
  "id": "spring",
  "name": "Spring Framework",
  "description": "Learn Spring Framework"
}
```

### 5. Create a Course

```bash
POST http://localhost:8080/topics/spring/courses
Authorization: Bearer {your-token}
Content-Type: application/json

{
  "id": "spring-boot",
  "name": "Spring Boot Basics",
  "description": "Introduction to Spring Boot"
}
```

### 6. Search Courses

```bash
GET http://localhost:8080/courses/search?keyword=spring&page=0&size=10
Authorization: Bearer {your-token}
```

## Database

The application uses H2 in-memory database. Access the H2 console at:

```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: (leave blank)
Password: (leave blank)
```

## Project Structure

```
src/main/java/com/crud/crud/
├── auth/              # JWT authentication & security
├── controller/        # REST controllers
├── service/           # Business logic
├── repository/        # Data access layer
├── model/            # JPA entities
└── exception/        # Global exception handling
```

## Security

- Passwords are hashed using BCrypt
- JWT tokens expire after 24 hours
- All endpoints except `/auth/**` require authentication
- CSRF protection disabled (stateless JWT)

## Future Enhancements

- Refresh token mechanism
- User roles and permissions
- Email verification
- Password reset functionality
- API documentation with Swagger

