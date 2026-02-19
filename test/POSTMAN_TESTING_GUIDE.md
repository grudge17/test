# 📮 Complete Postman Testing Guide

## 🎯 Prerequisites

- ✅ Application is running (you should see "Claims Processing System Started Successfully!")
- ✅ Postman is installed (download from: https://www.postman.com/downloads/)

---

## 🚀 Quick Start - Two Options

### **Option 1: Import the Collection (Easiest!)**

1. **Open Postman**
2. **Click "Import"** (top-left corner)
3. **Drag and drop** the file: `Claims-Processing-API.postman_collection.json`
   - Or click "Upload Files" and select it
4. **Click "Import"**
5. **Done!** You'll see "Claims Processing System API" in the left sidebar

### **Option 2: Create Requests Manually**

Follow the step-by-step guide below to create each request manually.

---

## 📋 Complete Testing Workflow

### **Test 1: Get All Users** ✅

**Purpose:** Verify the app is running and see sample data

**Steps:**
1. Click **"New"** → **"HTTP Request"**
2. Set method to: **GET**
3. Enter URL: `http://localhost:8080/api/users`
4. Click **"Send"**

**Expected Response (200 OK):**
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
  {
    "id": 2,
    "username": "jane_smith",
    "email": "jane@example.com",
    "fullName": "Jane Smith",
    "role": "USER",
    "createdAt": "2024-..."
  },
  {
    "id": 3,
    "username": "admin",
    "email": "admin@example.com",
    "fullName": "Admin User",
    "role": "ADMIN",
    "createdAt": "2024-..."
  }
]
```

✅ **Success:** You see 3 users (john_doe, jane_smith, admin)

---

### **Test 2: Get User by ID** ✅

**Purpose:** Test getting a specific user

**Steps:**
1. **New Request** → **GET**
2. URL: `http://localhost:8080/api/users/1`
3. Click **"Send"**

**Expected Response (200 OK):**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "role": "USER",
  "createdAt": "2024-..."
}
```

---

### **Test 3: Create a New User** ✅

**Purpose:** Test user creation

**Steps:**
1. **New Request** → **POST**
2. URL: `http://localhost:8080/api/users`
3. Click **"Headers"** tab
4. Add header:
   - Key: `Content-Type`
   - Value: `application/json`
5. Click **"Body"** tab
6. Select **"raw"**
7. Select **"JSON"** from dropdown
8. Paste this JSON:
```json
{
  "username": "test_user",
  "email": "test@example.com",
  "fullName": "Test User",
  "role": "USER"
}
```
9. Click **"Send"**

**Expected Response (201 Created):**
```json
{
  "id": 4,
  "username": "test_user",
  "email": "test@example.com",
  "fullName": "Test User",
  "role": "USER",
  "createdAt": "2024-..."
}
```

✅ **Success:** New user created with ID 4

---

### **Test 4: Create a Claim (User Submits)** 🎯

**Purpose:** User submits an insurance claim

**Steps:**
1. **New Request** → **POST**
2. URL: `http://localhost:8080/api/claims`
3. **Headers:**
   - Key: `Content-Type`
   - Value: `application/json`
4. **Body** → **raw** → **JSON**:
```json
{
  "description": "Car accident - front bumper damage",
  "claimAmount": 5000.00,
  "userId": 1
}
```
5. Click **"Send"**

**Expected Response (201 Created):**
```json
{
  "id": 1,
  "claimNumber": "CLM-XXXXXXXX",
  "description": "Car accident - front bumper damage",
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

✅ **Success:** Claim created with status PENDING
📝 **Note the claim ID** (you'll need it for next tests)

---

### **Test 5: Get All Claims** ✅

**Purpose:** Admin views all claims

**Steps:**
1. **New Request** → **GET**
2. URL: `http://localhost:8080/api/claims`
3. Click **"Send"**

**Expected Response (200 OK):**
```json
[
  {
    "id": 1,
    "claimNumber": "CLM-XXXXXXXX",
    "description": "Car accident - front bumper damage",
    "claimAmount": 5000.00,
    "status": "PENDING",
    "userName": "John Doe",
    "userId": 1,
    ...
  }
]
```

---

### **Test 6: Get Claims by User** ✅

**Purpose:** User checks their own claims

**Steps:**
1. **New Request** → **GET**
2. URL: `http://localhost:8080/api/claims/user/1`
3. Click **"Send"**

**Expected Response (200 OK):**
- Array of claims for user ID 1 (John Doe)

---

### **Test 7: Get Claims by Status** ✅

**Purpose:** Admin filters claims by status

**Steps:**
1. **New Request** → **GET**
2. URL: `http://localhost:8080/api/claims/status/PENDING`
3. Click **"Send"**

**Expected Response (200 OK):**
- Array of all PENDING claims

**Try other statuses:**
- `http://localhost:8080/api/claims/status/IN_PROGRESS`
- `http://localhost:8080/api/claims/status/APPROVED`
- `http://localhost:8080/api/claims/status/REJECTED`

---

### **Test 8: Admin Sets Claim to IN_PROGRESS** 🎯

**Purpose:** Admin starts reviewing a claim

**Steps:**
1. **New Request** → **PUT**
2. URL: `c`
   - Replace `1` with your claim ID
3. **Headers:**
   - Key: `Content-Type`
   - Value: `application/json`
4. **Body** → **raw** → **JSON**:
```json
{
  "status": "IN_PROGRESS",
  "reviewComments": "Reviewing claim documents and verifying details",
  "adminId": 3
}
```
5. Click **"Send"**

**Expected Response (200 OK):**
```json
{
  "id": 1,
  "claimNumber": "CLM-XXXXXXXX",
  "description": "Car accident - front bumper damage",
  "claimAmount": 5000.00,
  "status": "IN_PROGRESS",
  "userName": "John Doe",
  "userId": 1,
  "reviewedByName": "Admin User",
  "reviewComments": "Reviewing claim documents and verifying details",
  "createdAt": "2024-...",
  "reviewedAt": "2024-..."
}
```

✅ **Success:** Status changed to IN_PROGRESS

---

### **Test 9: Admin Approves Claim** 🎯

**Purpose:** Admin approves the claim

**Steps:**
1. **New Request** → **PUT**
2. URL: `http://localhost:8080/api/claims/1/review`
3. **Headers:**
   - Key: `Content-Type`
   - Value: `application/json`
4. **Body** → **raw** → **JSON**:
```json
{
  "status": "APPROVED",
  "reviewComments": "All documents verified. Claim approved for payment.",
  "adminId": 3
}
```
5. Click **"Send"**

**Expected Response (200 OK):**
```json
{
  "id": 1,
  "status": "APPROVED",
  "reviewedByName": "Admin User",
  "reviewComments": "All documents verified. Claim approved for payment.",
  "reviewedAt": "2024-..."
  ...
}
```

✅ **Success:** Claim approved!

---

### **Test 10: Admin Rejects Claim** ❌

**Purpose:** Admin rejects a claim

**Steps:**
1. Create another claim first (repeat Test 4)
2. **New Request** → **PUT**
3. URL: `http://localhost:8080/api/claims/2/review`
4. **Headers:**
   - Key: `Content-Type`
   - Value: `application/json`
5. **Body** → **raw** → **JSON**:
```json
{
  "status": "REJECTED",
  "reviewComments": "Insufficient documentation provided. Please resubmit with required documents.",
  "adminId": 3
}
```
6. Click **"Send"**

**Expected Response (200 OK):**
```json
{
  "id": 2,
  "status": "REJECTED",
  "reviewComments": "Insufficient documentation provided...",
  ...
}
```

---

## 🔄 Complete Workflow Test

**Test the entire claim lifecycle:**

### **Step 1: User Creates Claim**
```
POST http://localhost:8080/api/claims
Body:
{
  "description": "Medical expense claim",
  "claimAmount": 2500.00,
  "userId": 1
}
```

### **Step 2: Admin Views Pending Claims**
```
GET http://localhost:8080/api/claims/status/PENDING
```

### **Step 3: Admin Sets to IN_PROGRESS**
```
PUT http://localhost:8080/api/claims/1/review
Body:
{
  "status": "IN_PROGRESS",
  "reviewComments": "Under review",
  "adminId": 3
}
```

### **Step 4: Admin Approves**
```
PUT http://localhost:8080/api/claims/1/review
Body:
{
  "status": "APPROVED",
  "reviewComments": "Approved!",
  "adminId": 3
}
```

### **Step 5: User Checks Status**
```
GET http://localhost:8080/api/claims/user/1
```

---

## 💡 Postman Pro Tips

### **Save Your Requests**
1. After creating a request, click **"Save"**
2. Create a new collection: "Claims Processing Tests"
3. Save all requests in this collection

### **Use Variables**
1. Click the eye icon (top-right) → **"Edit"**
2. Add variable:
   - Variable: `baseUrl`
   - Initial Value: `http://localhost:8080`
3. Use in requests: `{{baseUrl}}/api/users`

### **Organize Requests**
Create folders in your collection:
- 📁 User Management
  - GET All Users
  - GET User by ID
  - POST Create User
- 📁 Claim Management
  - POST Create Claim
  - GET All Claims
  - PUT Review Claim

### **Test Scripts**
Add to the "Tests" tab to auto-verify responses:
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response has data", function () {
    pm.response.to.be.json;
});
```

---

## ✅ Testing Checklist

Complete this checklist to verify everything works:

- [ ] GET all users (should return 3 users)
- [ ] GET user by ID (should return john_doe)
- [ ] POST create new user (should return 201)
- [ ] POST create claim (should return 201 with PENDING status)
- [ ] GET all claims (should show your claim)
- [ ] GET claims by user (should show user's claims)
- [ ] GET claims by status PENDING (should show pending claims)
- [ ] PUT review claim to IN_PROGRESS (should update status)
- [ ] PUT review claim to APPROVED (should approve claim)
- [ ] POST create another claim and REJECT it

---

## 🐛 Troubleshooting

### **"Could not get any response"**
❌ **Problem:** Application not running

✅ **Solution:**
- Check if app is running in VS Code terminal
- Verify at: http://localhost:8080/api/users in browser

### **"404 Not Found"**
❌ **Problem:** Wrong URL

✅ **Solution:**
- Check URL spelling
- Make sure it starts with `http://localhost:8080`
- Verify endpoint exists

### **"400 Bad Request"**
❌ **Problem:** Invalid JSON or missing required fields

✅ **Solution:**
- Check JSON syntax (use JSON validator)
- Make sure all required fields are present
- Check Content-Type header is set

### **"500 Internal Server Error"**
❌ **Problem:** Server error

✅ **Solution:**
- Check VS Code terminal for error logs
- Verify userId exists before creating claim
- Check adminId exists before reviewing

---

## 📊 Expected Results Summary

| Test | Method | Endpoint | Expected Status |
|------|--------|----------|-----------------|
| Get All Users | GET | `/api/users` | 200 OK |
| Get User by ID | GET | `/api/users/1` | 200 OK |
| Create User | POST | `/api/users` | 201 Created |
| Create Claim | POST | `/api/claims` | 201 Created |
| Get All Claims | GET | `/api/claims` | 200 OK |
| Get User Claims | GET | `/api/claims/user/1` | 200 OK |
| Get Claims by Status | GET | `/api/claims/status/PENDING` | 200 OK |
| Review Claim | PUT | `/api/claims/1/review` | 200 OK |

---

## 🎉 Success!

If all tests pass, congratulations! Your Claims Processing System is working perfectly!

**Next Steps:**
- Test WebSocket notifications (see TESTING_GUIDE.md)
- Understand the code (see STEP_BY_STEP_WALKTHROUGH.md)
- Add more features!

---

**Happy Testing! 🚀**

