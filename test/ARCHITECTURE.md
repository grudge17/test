# 🏗️ System Architecture

## MVC Architecture Pattern

This application follows the **Model-View-Controller (MVC)** pattern with a layered architecture.

```
┌─────────────────────────────────────────────────────────────┐
│                         CLIENT LAYER                         │
│  (Web Browser, Postman, Mobile App, etc.)                   │
└─────────────────────────────────────────────────────────────┘
                            ↓ HTTP/WebSocket
┌─────────────────────────────────────────────────────────────┐
│                    CONTROLLER LAYER (View)                   │
│  ┌──────────────────┐         ┌──────────────────┐         │
│  │ UserController   │         │ ClaimController  │         │
│  │ - REST Endpoints │         │ - REST Endpoints │         │
│  └──────────────────┘         └──────────────────┘         │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                      SERVICE LAYER                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │ UserService  │  │ ClaimService │  │NotificationService│ │
│  │ - Business   │  │ - Business   │  │ - WebSocket      │  │
│  │   Logic      │  │   Logic      │  │   Notifications  │  │
│  └──────────────┘  └──────────────┘  └──────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    REPOSITORY LAYER                          │
│  ┌──────────────────┐         ┌──────────────────┐         │
│  │ UserRepository   │         │ ClaimRepository  │         │
│  │ - Data Access    │         │ - Data Access    │         │
│  └──────────────────┘         └──────────────────┘         │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                      MODEL LAYER                             │
│  ┌──────────────┐         ┌──────────────┐                 │
│  │ User Entity  │         │ Claim Entity │                 │
│  │ - JPA Model  │         │ - JPA Model  │                 │
│  └──────────────┘         └──────────────┘                 │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    DATABASE LAYER                            │
│                   H2 In-Memory Database                      │
└─────────────────────────────────────────────────────────────┘
```

## Component Breakdown

### 1. **Model Layer** (Domain Entities)
- **User.java**: Represents users and admins
- **Claim.java**: Represents insurance claims
- **ClaimStatus.java**: Enum for claim statuses
- **UserRole.java**: Enum for user roles

### 2. **Repository Layer** (Data Access)
- **UserRepository**: CRUD operations for users
- **ClaimRepository**: CRUD operations for claims
- Uses Spring Data JPA for automatic implementation

### 3. **Service Layer** (Business Logic)
- **UserService**: User management logic
- **ClaimService**: Claim processing logic
- **NotificationService**: Real-time notification logic

### 4. **Controller Layer** (View/API)
- **UserController**: REST endpoints for user operations
- **ClaimController**: REST endpoints for claim operations
- Returns JSON responses (View in MVC)

### 5. **DTO Layer** (Data Transfer Objects)
- **ClaimRequest**: For creating claims
- **ClaimResponse**: For returning claim data
- **ClaimReviewRequest**: For reviewing claims
- **UserRequest**: For creating users

## Request Flow Example

### User Creates a Claim:

```
1. Client sends POST request
   ↓
2. ClaimController.createClaim() receives request
   ↓
3. Validates ClaimRequest DTO
   ↓
4. Calls ClaimService.createClaim()
   ↓
5. ClaimService:
   - Validates user exists (calls UserService)
   - Creates Claim entity
   - Saves via ClaimRepository
   - Sends notification via NotificationService
   ↓
6. Returns ClaimResponse DTO
   ↓
7. Controller sends JSON response to client
   ↓
8. WebSocket sends real-time notification to user
```

### Admin Reviews Claim:

```
1. Client sends PUT request to /api/claims/{id}/review
   ↓
2. ClaimController.reviewClaim() receives request
   ↓
3. Validates ClaimReviewRequest DTO
   ↓
4. Calls ClaimService.reviewClaim()
   ↓
5. ClaimService:
   - Fetches claim from ClaimRepository
   - Validates admin exists (calls UserService)
   - Updates claim status and review details
   - Saves updated claim
   - Sends notification to claim owner
   ↓
6. Returns ClaimResponse DTO
   ↓
7. Controller sends JSON response to client
   ↓
8. WebSocket sends real-time notification to user
```

## Database Schema

### USERS Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);
```

### CLAIMS Table
```sql
CREATE TABLE claims (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    claim_number VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    claim_amount DECIMAL(19,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
    reviewed_by BIGINT,
    review_comments VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    reviewed_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (reviewed_by) REFERENCES users(id)
);
```

## Technology Stack

### Core Framework
- **Java 17**: Modern Java with latest features
- **Spring Boot 3.0**: Application framework
- **Spring MVC**: Web framework (Controller layer)

### Data Layer
- **Spring Data JPA**: Repository abstraction
- **Hibernate**: ORM implementation
- **H2 Database**: In-memory database

### Real-time Communication
- **Spring WebSocket**: WebSocket support
- **STOMP**: Messaging protocol
- **SockJS**: WebSocket fallback

### Utilities
- **Lombok**: Reduces boilerplate code
- **Jakarta Validation**: Input validation
- **Jackson**: JSON serialization

## Design Patterns Used

1. **MVC Pattern**: Separation of concerns
2. **Repository Pattern**: Data access abstraction
3. **Service Layer Pattern**: Business logic encapsulation
4. **DTO Pattern**: Data transfer between layers
5. **Dependency Injection**: Loose coupling via Spring
6. **Builder Pattern**: For creating complex objects (ClaimResponse)

## Key Features

### 1. Proper Layering
- Clear separation between layers
- Each layer has single responsibility
- Dependencies flow downward

### 2. RESTful API Design
- Standard HTTP methods (GET, POST, PUT)
- Resource-based URLs
- Proper status codes

### 3. Real-time Updates
- WebSocket for instant notifications
- User-specific notification channels
- Automatic status update broadcasting

### 4. Data Validation
- Input validation using Jakarta Validation
- Custom business rule validation
- Global exception handling

### 5. Clean Code
- Meaningful names
- Small, focused methods
- Proper documentation
- Consistent formatting

## Scalability Considerations

### Current Implementation (Learning/Development)
- In-memory H2 database
- Simple message broker
- Single instance

### Production Enhancements (Future)
- PostgreSQL/MySQL for persistence
- Redis for caching
- RabbitMQ/Kafka for messaging
- Load balancing
- Microservices architecture
- Docker containerization
- Kubernetes orchestration

---

This architecture provides a solid foundation for learning Spring Boot 3.0 and Java 17 while following industry best practices!

