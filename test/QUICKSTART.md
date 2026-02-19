# 🚀 Quick Start Guide

## Get Started in 5 Minutes!

### Step 1: Prerequisites Check ✅

Make sure you have:
- **Java 17** or higher installed
  ```bash
  java -version
  ```
  If not installed, download from: https://adoptium.net/

- **Maven** (or use your IDE)
  ```bash
  mvn -version
  ```

### Step 2: Run the Application 🏃

#### Option A: Using IntelliJ IDEA (Easiest!)
1. Open IntelliJ IDEA
2. Click "Open" and select the `c:\test` folder
3. Wait for dependencies to download (bottom right corner)
4. Find `ClaimsProcessingApplication.java` in the project explorer
5. Right-click → "Run 'ClaimsProcessingApplication'"
6. ✅ Done! Application is running!

#### Option B: Using Command Line
```bash
cd c:\test
mvn spring-boot:run
```

#### Option C: Using VS Code
1. Open VS Code
2. Install "Extension Pack for Java"
3. Open the `c:\test` folder
4. Press F5 or click Run → Start Debugging

### Step 3: Verify It's Running ✅

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

### Step 4: Test the APIs 🧪

#### Test 1: Get All Users
Open browser or use curl:
```bash
http://localhost:8080/api/users
```

#### Test 2: Create a Claim
```bash
curl -X POST http://localhost:8080/api/claims ^
  -H "Content-Type: application/json" ^
  -d "{\"description\": \"Car accident\", \"claimAmount\": 5000, \"userId\": 1}"
```

#### Test 3: View All Claims
```bash
http://localhost:8080/api/claims
```

### Step 5: Test Real-time Notifications 🔔

1. Open browser: http://localhost:8080/index.html
2. Enter User ID: `1`
3. Click "Connect"
4. Create a claim using the API (Step 4, Test 2)
5. Watch the notification appear in real-time! 🎉

## 🎯 Complete Workflow Test

### Scenario: User submits claim, Admin approves it

```bash
# 1. User creates a claim
curl -X POST http://localhost:8080/api/claims ^
  -H "Content-Type: application/json" ^
  -d "{\"description\": \"Medical expense\", \"claimAmount\": 2500, \"userId\": 1}"

# Note the claim ID from response (e.g., "id": 1)

# 2. Admin views pending claims
curl http://localhost:8080/api/claims/status/PENDING

# 3. Admin sets claim to IN_PROGRESS
curl -X PUT http://localhost:8080/api/claims/1/review ^
  -H "Content-Type: application/json" ^
  -d "{\"status\": \"IN_PROGRESS\", \"reviewComments\": \"Reviewing\", \"adminId\": 3}"

# 4. Admin approves the claim
curl -X PUT http://localhost:8080/api/claims/1/review ^
  -H "Content-Type: application/json" ^
  -d "{\"status\": \"APPROVED\", \"reviewComments\": \"Approved!\", \"adminId\": 3}"

# 5. User checks their claims
curl http://localhost:8080/api/claims/user/1
```

## 📊 Using Postman (Recommended for Beginners)

1. Download Postman: https://www.postman.com/downloads/
2. Import the collection: `Claims-Processing-API.postman_collection.json`
3. Click on any request and hit "Send"
4. See the response!

## 🗄️ View Database (Optional)

1. Open: http://localhost:8080/h2-console
2. Use these settings:
   - JDBC URL: `jdbc:h2:mem:claimsdb`
   - Username: `sa`
   - Password: (leave empty)
3. Click "Connect"
4. Run SQL: `SELECT * FROM CLAIMS;`

## 🎓 What You've Built

✅ RESTful API with Spring Boot 3.0
✅ Proper MVC architecture
✅ JPA entities and repositories
✅ Service layer with business logic
✅ Real-time WebSocket notifications
✅ Complete CRUD operations
✅ Proper error handling
✅ Input validation

## 📚 Next Steps

1. **Add Unit Tests**
   - Create tests using JUnit 5 and Mockito
   - Aim for 90%+ code coverage

2. **Add Security**
   - Implement Spring Security
   - Add JWT authentication

3. **Add Caching**
   - Use Redis with Spring Cache
   - Add @Cacheable annotations

4. **Containerize**
   - Create Dockerfile
   - Use Docker Compose

5. **Add CI/CD**
   - Set up Jenkins pipeline
   - Automate testing and deployment

## 🐛 Troubleshooting

### "Port 8080 already in use"
Change port in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### "Java version not compatible"
Make sure you have Java 17+:
```bash
java -version
```

### "Maven not found"
Use your IDE instead, or install Maven from:
https://maven.apache.org/download.cgi

### "Dependencies not downloading"
```bash
mvn clean install -U
```

## 💡 Tips

- Use Postman for easier API testing
- Keep the WebSocket test page open to see real-time updates
- Check H2 console to see database changes
- Read the logs for debugging

## 📖 Documentation

- **README.md** - Project overview
- **ARCHITECTURE.md** - Detailed architecture explanation
- **TESTING_GUIDE.md** - Comprehensive testing instructions
- **QUICKSTART.md** - This file!

---

**Happy Coding! 🎉**

If you have any questions, check the documentation or review the code comments.

