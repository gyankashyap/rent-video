# RentVideo - Video Rental System

A RESTful API service developed using Spring Boot and MySQL to manage an online video rental system.

## Features

- User Authentication and Authorization (Basic Auth)
- Two user roles: CUSTOMER and ADMIN
- Public and Private API endpoints
- User Registration and Login
- Video Management (Create, Read, Update, Delete)

## API Endpoints

### Public Endpoints

- `POST /api/auth/register` - Register a new user
- `GET /api/videos` - Get all available videos

### Private Endpoints

- `GET /api/auth/me` - Get current user details
- `GET /api/videos/{id}` - Get a specific video by ID

### Admin Endpoints

- `POST /api/admin/videos` - Create a new video
- `PUT /api/admin/videos/{id}` - Update a video
- `DELETE /api/admin/videos/{id}` - Delete a video
- `GET /api/admin/videos/all` - Get all videos (including unavailable ones)

## Database Schema

### Users Table
- id (Primary Key)
- email (Unique)
- password (BCrypt encoded)
- first_name
- last_name
- role (CUSTOMER or ADMIN)

### Videos Table
- id (Primary Key)
- title
- director
- genre
- availability_status (boolean)

## Setup and Installation

1. Configure MySQL database properties in `application.properties`
2. Run the application using: `./gradlew bootRun`
3. The application will start at: `http://localhost:8080`

## Authentication

The application uses Basic Authentication:
- Include an Authorization header with Base64 encoded credentials (email:password)
- Example: `Authorization: Basic dXNlckBleGFtcGxlLmNvbTpwYXNzd29yZA==`