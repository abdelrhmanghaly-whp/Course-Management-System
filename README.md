# CRUD Application

A Spring Boot REST API for managing topics and courses. Each course belongs to a topic.

## Tech Stack

- Spring Boot 3.5.7
- Spring Data JPA
- H2 Database
- Java 21

## Running

```bash
./mvnw spring-boot:run
```

Application runs on `http://localhost:8080`

## API Endpoints

### Topics

```
GET    /topics
GET    /topics/{id}
POST   /topics
PUT    /topics/{id}
DELETE /topics/{id}
```

### Courses

```
GET    /topics/{topicId}/courses
GET    /topics/{topicId}/courses/{courseId}
POST   /topics/{topicId}/courses
PUT    /topics/{topicId}/courses/{id}
DELETE /topics/{topicId}/courses/{id}
```

## Example

Create a topic:
```json
POST /topics

{
  "id": "spring",
  "name": "Spring Framework",
  "description": "Spring Framework fundamentals"
}
```

Create a course:
```json
POST /topics/spring/courses

{
  "id": "spring-boot",
  "name": "Spring Boot Basics",
  "description": "Introduction to Spring Boot"
}
```

## H2 Console

Access at `http://localhost:8080/h2-console`

- URL: `jdbc:h2:mem:testdb`
- Username: (blank)
- Password: (blank)
