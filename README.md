# 🏥 Insurance Claims Processing System

A modern insurance claims processing system built with **Java 17** and **Spring Boot 3.0** following proper **MVC architecture**.

## 🎯 Features

1. **User Management** - Create and manage users (regular users and admins)
2. **Claim Submission** - Users can submit insurance claims
3. **Claim Review** - Admins can review and update claim status (PENDING, IN_PROGRESS, APPROVED, REJECTED)
4. **Real-time Notifications** - Users receive instant updates via WebSocket when claim status changes
5. **RESTful APIs** - Clean REST endpoints for all operations

## 🏗️ Architecture

### MVC Pattern
- **Model**: JPA entities (User, Claim)
- **View**: REST API responses (JSON)
- **Controller**: REST controllers handling HTTP requests

### Layers
```
Controller Layer → Service Layer → Repository Layer → Database
```

## 🛠️ Technologies

- Java 17
- Spring Boot 3.0.13
- Spring Data JPA
- Spring WebSocket
- H2 Database (in-memory)
- Lombok
- Maven

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+

## 🚀 Running the Application

### **📘 IMPORTANT: Read This First!**

Before running, you need **Java 17** installed. See detailed guides:

- **📥 [INSTALLATION_GUIDE.md](INSTALLATION_GUIDE.md)** - Install Java 17
- **🎯 [RUN_APPLICATION.md](RUN_APPLICATION.md)** - How to run (3 methods)
- **📘 [VSCODE_SETUP_GUIDE.md](VSCODE_SETUP_GUIDE.md)** - VS Code visual guide

### **Quick Start (VS Code - Recommended):**

1. **Install Java 17** (see INSTALLATION_GUIDE.md)
2. **Open VS Code** → Open Folder → Select `c:\test`
3. **Open** `ClaimsProcessingApplication.java`
4. **Click** the "Run" button above the `main` method
5. **Wait** for success message in terminal
6. **Test** at http://localhost:8080/api/users

### **Alternative Methods:**

**Using Maven Wrapper (No Maven installation needed):**
```bash
.\mvnw.cmd spring-boot:run
```

**Using Maven (If installed):**
```bash
mvn clean install
mvn spring-boot:run
```

### **Access Points:**
- API Base URL: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
- Test Client: http://localhost:8080/index.html

## 📊 Sample Data

The application initializes with sample data:
- **User 1**: john_doe (ID: 1) - Regular User
- **User 2**: jane_smith (ID: 2) - Regular User
- **Admin**: admin (ID: 3) - Admin User

## 🔌 API Endpoints

### User Endpoints

#### Get All Users
```bash
curl -X GET http://localhost:8080/api/users
```

#### Get User by ID
```bash
curl -X GET http://localhost:8080/api/users/1
```

#### Create New User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "test_user",
    "email": "test@example.com",
    "fullName": "Test User",
    "role": "USER"
  }'
```

### Claim Endpoints

#### Create a Claim (User submits)
```bash
curl -X POST http://localhost:8080/api/claims \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Car accident claim",
    "claimAmount": 5000.00,
    "userId": 1
  }'
```

#### Get All Claims
```bash
curl -X GET http://localhost:8080/api/claims
```

#### Get Claims by User
```bash
curl -X GET http://localhost:8080/api/claims/user/1
```

#### Get Claims by Status
```bash
curl -X GET http://localhost:8080/api/claims/status/PENDING
```

#### Review Claim (Admin)
```bash
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d '{
    "status": "APPROVED",
    "reviewComments": "Claim approved after verification",
    "adminId": 3
  }'
```

## 🔄 Complete Workflow Example

### Step 1: User Creates a Claim
```bash
curl -X POST http://localhost:8080/api/claims \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Medical expense claim",
    "claimAmount": 2500.00,
    "userId": 1
  }'
```

### Step 2: Admin Views All Pending Claims
```bash
curl -X GET http://localhost:8080/api/claims/status/PENDING
```

### Step 3: Admin Updates Claim to IN_PROGRESS
```bash
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d '{
    "status": "IN_PROGRESS",
    "reviewComments": "Under review",
    "adminId": 3
  }'
```

### Step 4: Admin Approves the Claim
```bash
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d '{
    "status": "APPROVED",
    "reviewComments": "All documents verified. Claim approved.",
    "adminId": 3
  }'
```

### Step 5: User Checks Their Claims
```bash
curl -X GET http://localhost:8080/api/claims/user/1
```

## 🔔 Real-time Notifications

The system uses WebSocket for real-time notifications. When a claim status changes, the user receives instant notification.

### Testing WebSocket:
1. Open http://localhost:8080/index.html
2. Enter User ID (e.g., 1)
3. Click "Connect"
4. Submit a claim or update claim status via API
5. See real-time notifications appear!

## 📁 Project Structure

```
src/main/java/com/insurance/claims/
├── ClaimsProcessingApplication.java    # Main application class
├── config/
│   ├── DataInitializer.java           # Sample data initialization
│   └── WebSocketConfig.java           # WebSocket configuration
├── controller/
│   ├── ClaimController.java           # Claim REST endpoints
│   └── UserController.java            # User REST endpoints
├── dto/
│   ├── ClaimRequest.java              # Claim creation DTO
│   ├── ClaimResponse.java             # Claim response DTO
│   ├── ClaimReviewRequest.java        # Claim review DTO
│   └── UserRequest.java               # User creation DTO
├── exception/
│   └── GlobalExceptionHandler.java    # Global error handling
├── model/
│   ├── Claim.java                     # Claim entity
│   ├── ClaimStatus.java               # Claim status enum
│   ├── User.java                      # User entity
│   └── UserRole.java                  # User role enum
├── repository/
│   ├── ClaimRepository.java           # Claim data access
│   └── UserRepository.java            # User data access
└── service/
    ├── ClaimService.java              # Claim business logic
    ├── NotificationService.java       # WebSocket notifications
    └── UserService.java               # User business logic
```

## 🎓 Learning Objectives Covered

✅ Java 17 features (Records can be added for DTOs)
✅ Spring Boot 3.0 framework
✅ MVC architecture pattern
✅ RESTful API design
✅ Spring Data JPA
✅ WebSocket for real-time communication
✅ Proper layered architecture
✅ Exception handling
✅ Validation
✅ Dependency injection

## 🧪 Next Steps

1. Add unit tests with JUnit 5 and Mockito
2. Implement Spring Security for authentication
3. Add Redis caching with Spring AOP
4. Integrate with external APIs (like Claude API)
5. Add Docker containerization
6. Set up CI/CD with Jenkins

---

**Built with ❤️ using Java 17 and Spring Boot 3.0**

