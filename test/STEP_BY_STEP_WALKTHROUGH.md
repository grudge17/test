# 🎓 Step-by-Step Code Walkthrough

This document walks you through every component of the Claims Processing System to help you understand how everything works together.

## 📚 Table of Contents
1. [Project Setup](#1-project-setup)
2. [Model Layer](#2-model-layer)
3. [Repository Layer](#3-repository-layer)
4. [Service Layer](#4-service-layer)
5. [Controller Layer](#5-controller-layer)
6. [Configuration](#6-configuration)
7. [How It All Works Together](#7-how-it-all-works-together)

---

## 1. Project Setup

### pom.xml - Maven Dependencies
This file defines all the libraries our project needs:

**Key Dependencies:**
- `spring-boot-starter-web` - For REST APIs
- `spring-boot-starter-data-jpa` - For database operations
- `spring-boot-starter-websocket` - For real-time notifications
- `h2` - In-memory database
- `lombok` - Reduces boilerplate code

**Java Version:** 17 (specified in properties)

---

## 2. Model Layer (Domain Entities)

### User.java - User Entity
```java
@Entity - Marks this as a database table
@Table(name = "users") - Table name in database
```

**Fields:**
- `id` - Primary key (auto-generated)
- `username` - Unique username
- `email` - User's email
- `fullName` - Full name
- `role` - USER or ADMIN (enum)
- `createdAt` - Timestamp when created

**Key Annotations:**
- `@Id` - Primary key
- `@GeneratedValue` - Auto-increment
- `@Column(nullable = false)` - Required field
- `@Enumerated(EnumType.STRING)` - Store enum as string
- `@PrePersist` - Runs before saving to DB

### Claim.java - Claim Entity
**Fields:**
- `id` - Primary key
- `claimNumber` - Unique claim identifier (CLM-XXXXXXXX)
- `description` - What the claim is for
- `claimAmount` - Amount being claimed
- `status` - PENDING, IN_PROGRESS, APPROVED, REJECTED
- `user` - Who submitted the claim (relationship)
- `reviewedBy` - Admin who reviewed (relationship)
- `reviewComments` - Admin's comments
- `createdAt` - When claim was created
- `reviewedAt` - When claim was reviewed

**Key Relationships:**
- `@ManyToOne` - Many claims can belong to one user
- `@JoinColumn` - Foreign key column

### Enums
- **UserRole**: USER, ADMIN
- **ClaimStatus**: PENDING, IN_PROGRESS, APPROVED, REJECTED

---

## 3. Repository Layer (Data Access)

### UserRepository.java
```java
public interface UserRepository extends JpaRepository<User, Long>
```

**What it does:**
- Extends `JpaRepository` - Spring automatically implements CRUD operations
- No need to write SQL!

**Built-in methods (from JpaRepository):**
- `save(user)` - Create or update
- `findById(id)` - Find by ID
- `findAll()` - Get all users
- `deleteById(id)` - Delete user

**Custom methods:**
- `findByUsername(String username)` - Spring generates SQL automatically!
- `findByRole(UserRole role)` - Find all users with a role
- `existsByUsername(String username)` - Check if username exists

### ClaimRepository.java
**Custom methods:**
- `findByUser(User user)` - All claims by a user
- `findByStatus(ClaimStatus status)` - All claims with a status
- `findByClaimNumber(String claimNumber)` - Find by claim number
- `findByUserOrderByCreatedAtDesc(User user)` - User's claims, newest first
- `findAllByOrderByCreatedAtDesc()` - All claims, newest first

---

## 4. Service Layer (Business Logic)

### UserService.java

**Purpose:** Contains business logic for user operations

**Key Methods:**

1. **createUser(UserRequest request)**
   - Checks if username already exists
   - Creates new User entity
   - Saves to database
   - Returns saved user

2. **getUserById(Long id)**
   - Finds user by ID
   - Throws exception if not found

3. **getAllUsers()**
   - Returns all users

**Why use Service layer?**
- Separates business logic from controllers
- Reusable across different controllers
- Easier to test
- Can add validation, logging, etc.

### ClaimService.java

**Key Methods:**

1. **createClaim(ClaimRequest request)**
   ```
   - Validate user exists
   - Create Claim entity
   - Generate claim number (CLM-XXXXXXXX)
   - Set status to PENDING
   - Save to database
   - Send notification to user
   - Return ClaimResponse
   ```

2. **reviewClaim(Long claimId, ClaimReviewRequest request)**
   ```
   - Find claim by ID
   - Validate admin exists
   - Update claim status
   - Add review comments
   - Set reviewedBy and reviewedAt
   - Save updated claim
   - Notify user of status change
   - Return ClaimResponse
   ```

3. **Helper Methods:**
   - `generateClaimNumber()` - Creates unique claim number
   - `convertToResponse(Claim claim)` - Converts entity to DTO

### NotificationService.java

**Purpose:** Send real-time notifications via WebSocket

**Methods:**
- `notifyUser(Long userId, String message)` - Send to specific user
- `notifyAllAdmins(String message)` - Broadcast to all admins

**How it works:**
- Uses `SimpMessagingTemplate` from Spring WebSocket
- Sends to topic: `/topic/user/{userId}`
- Client subscribes to this topic to receive messages

---

## 5. Controller Layer (REST APIs)

### UserController.java

**Base URL:** `/api/users`

**Endpoints:**

1. **POST /api/users** - Create user
   - Receives: UserRequest JSON
   - Returns: User entity
   - Status: 201 Created

2. **GET /api/users/{id}** - Get user by ID
   - Returns: User entity
   - Status: 200 OK

3. **GET /api/users** - Get all users
   - Returns: List of users
   - Status: 200 OK

**Key Annotations:**
- `@RestController` - Marks as REST controller
- `@RequestMapping("/api/users")` - Base URL
- `@PostMapping` - Handle POST requests
- `@GetMapping` - Handle GET requests
- `@RequestBody` - Parse JSON from request body
- `@PathVariable` - Extract from URL path
- `@Valid` - Validate input

### ClaimController.java

**Base URL:** `/api/claims`

**Endpoints:**

1. **POST /api/claims** - User creates claim
2. **PUT /api/claims/{id}/review** - Admin reviews claim
3. **GET /api/claims** - Get all claims
4. **GET /api/claims/{id}** - Get specific claim
5. **GET /api/claims/user/{userId}** - Get user's claims
6. **GET /api/claims/status/{status}** - Get claims by status

---

## 6. Configuration

### application.properties

**Database Configuration:**
```properties
spring.datasource.url=jdbc:h2:mem:claimsdb  # In-memory database
spring.jpa.hibernate.ddl-auto=create-drop    # Recreate tables on startup
```

**H2 Console:**
```properties
spring.h2.console.enabled=true               # Enable web console
spring.h2.console.path=/h2-console           # Access URL
```

### WebSocketConfig.java

**Purpose:** Configure WebSocket for real-time notifications

**Configuration:**
- Message broker: `/topic`
- Application prefix: `/app`
- STOMP endpoint: `/ws`
- Allows SockJS fallback

### DataInitializer.java

**Purpose:** Create sample data on startup

**Creates:**
- 2 regular users (john_doe, jane_smith)
- 1 admin user (admin)

**Implements:** `CommandLineRunner` - runs after application starts

---

## 7. How It All Works Together

### Example: User Creates a Claim

**Step 1: Client sends request**
```bash
POST /api/claims
{
  "description": "Car accident",
  "claimAmount": 5000,
  "userId": 1
}
```

**Step 2: ClaimController receives request**
```java
@PostMapping
public ResponseEntity<ClaimResponse> createClaim(@Valid @RequestBody ClaimRequest request)
```
- `@Valid` validates the request
- Calls `claimService.createClaim(request)`

**Step 3: ClaimService processes**
```java
public ClaimResponse createClaim(ClaimRequest request) {
    // 1. Get user from database
    User user = userService.getUserById(request.getUserId());
    
    // 2. Create claim entity
    Claim claim = new Claim();
    claim.setClaimNumber(generateClaimNumber());
    claim.setDescription(request.getDescription());
    claim.setClaimAmount(request.getClaimAmount());
    claim.setStatus(ClaimStatus.PENDING);
    claim.setUser(user);
    
    // 3. Save to database
    Claim savedClaim = claimRepository.save(claim);
    
    // 4. Send notification
    notificationService.notifyUser(user.getId(), "Claim created");
    
    // 5. Return response
    return convertToResponse(savedClaim);
}
```

**Step 4: ClaimRepository saves to database**
- Spring Data JPA generates SQL
- Saves to H2 database

**Step 5: NotificationService sends WebSocket message**
- Sends to `/topic/user/1`
- User's browser receives notification instantly

**Step 6: Controller returns response**
```json
{
  "id": 1,
  "claimNumber": "CLM-ABC12345",
  "description": "Car accident",
  "claimAmount": 5000.00,
  "status": "PENDING",
  "userName": "John Doe",
  "userId": 1,
  ...
}
```

### Example: Admin Reviews Claim

**Request:**
```bash
PUT /api/claims/1/review
{
  "status": "APPROVED",
  "reviewComments": "All good!",
  "adminId": 3
}
```

**Flow:**
1. ClaimController → ClaimService
2. ClaimService finds claim by ID
3. Updates status, comments, reviewedBy, reviewedAt
4. Saves to database
5. Sends notification to user
6. Returns updated claim

**User receives notification:**
"Claim CLM-ABC12345 status updated to: APPROVED"

---

## 🎯 Key Concepts Explained

### 1. Dependency Injection
```java
@RequiredArgsConstructor  // Lombok generates constructor
public class ClaimService {
    private final ClaimRepository claimRepository;  // Injected by Spring
    private final UserService userService;          // Injected by Spring
}
```

### 2. DTOs (Data Transfer Objects)
- **Request DTOs**: What client sends (ClaimRequest)
- **Response DTOs**: What server returns (ClaimResponse)
- **Why?** Don't expose internal entities directly

### 3. Exception Handling
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(...)
}
```
- Catches all exceptions
- Returns consistent error format

### 4. Validation
```java
@NotBlank(message = "Description is required")
private String description;
```
- Validates input automatically
- Returns 400 Bad Request if invalid

---

## 🎓 What You've Learned

✅ Spring Boot project structure
✅ MVC architecture pattern
✅ JPA entities and relationships
✅ Spring Data repositories
✅ Service layer pattern
✅ REST controller design
✅ WebSocket configuration
✅ Dependency injection
✅ Exception handling
✅ Input validation

---

**Next:** Run the application and test each endpoint to see this in action!

