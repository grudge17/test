# 🧪 Testing Guide - Claims Processing System

## Prerequisites

You need one of the following to run this application:

### Option 1: Maven (Recommended)
- Install Maven from: https://maven.apache.org/download.cgi
- Add Maven to your PATH

### Option 2: Use IDE
- IntelliJ IDEA (Community or Ultimate)
- Eclipse with Spring Tools
- VS Code with Java Extension Pack

### Option 3: Use Maven Wrapper (if available)
```bash
./mvnw spring-boot:run    # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

## 🚀 Step-by-Step Testing Instructions

### Step 1: Build and Run the Application

#### Using Maven:
```bash
mvn clean install
mvn spring-boot:run
```

#### Using IntelliJ IDEA:
1. Open the project folder in IntelliJ
2. Wait for Maven to download dependencies
3. Right-click on `ClaimsProcessingApplication.java`
4. Select "Run 'ClaimsProcessingApplication'"

#### Using VS Code:
1. Open the project folder
2. Install "Extension Pack for Java" if not installed
3. Press F5 or click "Run" → "Start Debugging"

### Step 2: Verify Application Started

You should see:
```
==============================================
Claims Processing System Started Successfully!
==============================================
Sample Data Initialized:
Users: john_doe (ID: 1), jane_smith (ID: 2)
Admin: admin (ID: 3)
==============================================
```

### Step 3: Test REST APIs

#### Test 1: Get All Users
```bash
curl http://localhost:8080/api/users
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe",
    "role": "USER",
    "createdAt": "2024-..."
  },
  ...
]
```

#### Test 2: Create a Claim (User submits)
```bash
curl -X POST http://localhost:8080/api/claims \
  -H "Content-Type: application/json" \
  -d "{\"description\": \"Car accident claim\", \"claimAmount\": 5000.00, \"userId\": 1}"
```

**Expected Response:**
```json
{
  "id": 1,
  "claimNumber": "CLM-XXXXXXXX",
  "description": "Car accident claim",
  "claimAmount": 5000.00,
  "status": "PENDING",
  "userName": "John Doe",
  "userId": 1,
  "reviewedByName": null,
  "reviewComments": null,
  "createdAt": "2024-...",
  "reviewedAt": null
}
```

#### Test 3: Admin Views All Claims
```bash
curl http://localhost:8080/api/claims
```

#### Test 4: Admin Reviews Claim (Set to IN_PROGRESS)
```bash
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d "{\"status\": \"IN_PROGRESS\", \"reviewComments\": \"Under review\", \"adminId\": 3}"
```

#### Test 5: Admin Approves Claim
```bash
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d "{\"status\": \"APPROVED\", \"reviewComments\": \"Claim approved\", \"adminId\": 3}"
```

#### Test 6: User Checks Their Claims
```bash
curl http://localhost:8080/api/claims/user/1
```

### Step 4: Test Real-time Notifications

1. Open browser: http://localhost:8080/index.html
2. Enter User ID: `1`
3. Click "Connect"
4. In another terminal, create or update a claim for user 1
5. Watch notifications appear in real-time!

### Step 5: Test Complete Workflow

```bash
# 1. User creates a claim
curl -X POST http://localhost:8080/api/claims \
  -H "Content-Type: application/json" \
  -d "{\"description\": \"Medical expense\", \"claimAmount\": 2500.00, \"userId\": 1}"

# 2. Get the claim ID from response (let's say it's 1)

# 3. Admin sets to IN_PROGRESS
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d "{\"status\": \"IN_PROGRESS\", \"reviewComments\": \"Reviewing documents\", \"adminId\": 3}"

# 4. Admin approves
curl -X PUT http://localhost:8080/api/claims/1/review \
  -H "Content-Type: application/json" \
  -d "{\"status\": \"APPROVED\", \"reviewComments\": \"All good!\", \"adminId\": 3}"

# 5. User checks status
curl http://localhost:8080/api/claims/user/1
```

## 🔍 Using Postman (Alternative to curl)

### Import these requests into Postman:

1. **GET All Users**
   - Method: GET
   - URL: http://localhost:8080/api/users

2. **POST Create Claim**
   - Method: POST
   - URL: http://localhost:8080/api/claims
   - Headers: Content-Type: application/json
   - Body (raw JSON):
   ```json
   {
     "description": "Car accident claim",
     "claimAmount": 5000.00,
     "userId": 1
   }
   ```

3. **PUT Review Claim**
   - Method: PUT
   - URL: http://localhost:8080/api/claims/1/review
   - Headers: Content-Type: application/json
   - Body (raw JSON):
   ```json
   {
     "status": "APPROVED",
     "reviewComments": "Claim approved after verification",
     "adminId": 3
   }
   ```

## 🗄️ Access H2 Database Console

1. Open: http://localhost:8080/h2-console
2. JDBC URL: `jdbc:h2:mem:claimsdb`
3. Username: `sa`
4. Password: (leave empty)
5. Click "Connect"

### Useful SQL Queries:
```sql
-- View all users
SELECT * FROM USERS;

-- View all claims
SELECT * FROM CLAIMS;

-- View claims with user details
SELECT c.*, u.full_name 
FROM CLAIMS c 
JOIN USERS u ON c.user_id = u.id;
```

## ✅ Expected Behavior

### User Flow:
1. User submits a claim → Status: PENDING
2. User receives notification: "Claim CLM-XXX created successfully"
3. User can view their claims

### Admin Flow:
1. Admin views all pending claims
2. Admin updates claim to IN_PROGRESS
3. User receives notification: "Claim CLM-XXX status updated to: IN_PROGRESS"
4. Admin approves/rejects claim
5. User receives notification: "Claim CLM-XXX status updated to: APPROVED/REJECTED"

## 🐛 Troubleshooting

### Port 8080 already in use:
Change port in `application.properties`:
```properties
server.port=8081
```

### Maven dependencies not downloading:
```bash
mvn clean install -U
```

### Application not starting:
- Check Java version: `java -version` (should be 17+)
- Check logs for errors
- Ensure no other application is using port 8080

## 📝 Notes

- The H2 database is in-memory, so data is lost when application stops
- Sample users are created automatically on startup
- All timestamps are in UTC
- WebSocket endpoint: ws://localhost:8080/ws

---

Happy Testing! 🎉

