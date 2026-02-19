# 📘 VS Code Setup Guide - Visual Walkthrough

## 🎯 Complete Setup for Running the Application in VS Code

---

## Step 1: Install Java 17

### **Download and Install:**

1. Go to: **https://learn.microsoft.com/en-us/java/openjdk/download**
2. Download: **Microsoft Build of OpenJDK 17 (LTS)** for Windows x64
3. Run the installer
4. ✅ **IMPORTANT:** Check "Add to PATH" during installation
5. Click "Install" and wait for completion

### **Verify Installation:**

Open PowerShell and run:
```powershell
java -version
```

**Expected Output:**
```
openjdk version "17.0.x"
```

✅ If you see this, Java is installed correctly!
❌ If you see "not recognized", restart PowerShell and try again

---

## Step 2: Verify VS Code Extensions

### **Check Installed Extensions:**

1. Open VS Code
2. Click the Extensions icon (left sidebar) or press `Ctrl + Shift + X`
3. Make sure you have: **Extension Pack for Java**
4. It should show "Installed"

### **What's Included:**

The Extension Pack for Java includes:
- ✅ Language Support for Java
- ✅ Debugger for Java
- ✅ Test Runner for Java
- ✅ Maven for Java
- ✅ Project Manager for Java
- ✅ IntelliCode

---

## Step 3: Open the Project in VS Code

### **Open Folder:**

1. Click **File** → **Open Folder**
2. Navigate to: `c:\test`
3. Click **Select Folder**

### **What You'll See:**

- Explorer panel on the left shows the project structure
- You'll see folders: `src`, `.mvn`, and files: `pom.xml`, `README.md`, etc.

### **Wait for Java Extension to Load:**

- Look at the **bottom-right corner**
- You'll see: "Java" with a loading icon
- Wait until it says: "Ready" or shows a checkmark
- **First time:** May take 1-3 minutes (downloading dependencies)
- **Subsequent times:** 10-30 seconds

---

## Step 4: Navigate to Main Class

### **Find the Main Application File:**

1. In the **Explorer** panel (left side)
2. Expand: `src` → `main` → `java` → `com` → `insurance` → `claims`
3. Click on: **`ClaimsProcessingApplication.java`**

### **What You'll See:**

The file will open in the editor showing:
```java
@SpringBootApplication
public class ClaimsProcessingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClaimsProcessingApplication.class, args);
        ...
    }
}
```

---

## Step 5: Run the Application

### **Method 1: Using the Run Button (Easiest)**

1. Look **above** the `main` method
2. You'll see: `▶️ Run | 🐛 Debug`
3. Click **Run**

### **Method 2: Using Right-Click**

1. Right-click anywhere in the `ClaimsProcessingApplication.java` file
2. Select: **"Run Java"**

### **Method 3: Using Keyboard Shortcut**

1. Make sure `ClaimsProcessingApplication.java` is open
2. Press: `F5` (or `Ctrl + F5` for run without debugging)

### **Method 4: Using Command Palette**

1. Press: `Ctrl + Shift + P`
2. Type: **"Java: Run Java"**
3. Press Enter

---

## Step 6: Watch the Terminal

### **Terminal Opens Automatically:**

- A terminal panel opens at the bottom of VS Code
- You'll see Maven downloading dependencies (first time only)
- Lots of text will scroll by

### **Look for Success Messages:**

After 30-60 seconds (first time) or 5-10 seconds (subsequent runs), you should see:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.0.13)

...

==============================================
Claims Processing System Started Successfully!
==============================================
Sample Data Initialized:
Users: john_doe (ID: 1), jane_smith (ID: 2)
Admin: admin (ID: 3)
==============================================

...

Started ClaimsProcessingApplication in 5.234 seconds (JVM running for 6.123)
```

✅ **Success!** The application is now running!

---

## Step 7: Test the Application

### **Test 1: Browser Test**

1. Open your web browser
2. Go to: **http://localhost:8080/api/users**
3. You should see JSON data with 3 users

**Expected Output:**
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

### **Test 2: WebSocket Test**

1. Open browser
2. Go to: **http://localhost:8080/index.html**
3. Enter User ID: **1**
4. Click **Connect**
5. Status should change to: **Connected** (green)

---

## Step 8: Stop the Application

### **To Stop:**

1. Go to the Terminal panel in VS Code
2. Click the **trash can icon** 🗑️ (top-right of terminal)
3. Or press `Ctrl + C` in the terminal

### **To Restart:**

1. Click the **Run** button again
2. Or press `F5`

---

## 🎨 VS Code Layout Overview

```
┌─────────────────────────────────────────────────────────┐
│  File  Edit  View  ...                          - □ X   │
├──────┬──────────────────────────────────────────────────┤
│      │  ClaimsProcessingApplication.java        ▶️ Run  │
│ 📁   │  ─────────────────────────────────────────────── │
│ src  │  package com.insurance.claims;                   │
│  └main│                                                  │
│   └java│  @SpringBootApplication                        │
│    └com│  public class ClaimsProcessingApplication {   │
│     └insurance│      public static void main(...) {     │
│      └claims│          SpringApplication.run(...);      │
│       📄 ClaimsProcessingApplication.java              │
│       📁 config                                         │
│       📁 controller                                     │
│       📁 service                                        │
│       📁 repository                                     │
│       📁 model                                          │
├──────┴──────────────────────────────────────────────────┤
│  TERMINAL                                        🗑️ ⚙️  │
│  ─────────────────────────────────────────────────────  │
│  Started ClaimsProcessingApplication in 5.234 seconds   │
│  Claims Processing System Started Successfully!         │
└─────────────────────────────────────────────────────────┘
```

---

## 🐛 Common Issues and Solutions

### **Issue 1: "Run" button not showing**

**Solution:**
1. Make sure you're in `ClaimsProcessingApplication.java`
2. Wait for Java extension to fully load (bottom-right corner)
3. Press `Ctrl + Shift + P` → "Java: Clean Java Language Server Workspace"
4. Restart VS Code

### **Issue 2: "Cannot resolve symbol" errors**

**Solution:**
1. Press `Ctrl + Shift + P`
2. Type: "Java: Clean Workspace"
3. Wait for reload
4. If still not working, close and reopen VS Code

### **Issue 3: Application won't start**

**Solution:**
1. Check terminal for error messages
2. Make sure Java 17 is installed: `java -version`
3. Make sure port 8080 is not in use
4. Try running from PowerShell: `.\mvnw.cmd spring-boot:run`

### **Issue 4: Dependencies not downloading**

**Solution:**
1. Check internet connection
2. Wait longer (first time can take 5-10 minutes)
3. Look for download progress in terminal
4. If stuck, press `Ctrl + C` and try again

---

## ✅ Success Checklist

- [ ] Java 17 installed and verified
- [ ] VS Code opened with `c:\test` folder
- [ ] Java extension loaded (bottom-right shows "Ready")
- [ ] `ClaimsProcessingApplication.java` file open
- [ ] Clicked "Run" button
- [ ] Terminal shows success message
- [ ] Browser shows users at http://localhost:8080/api/users

---

## 🎯 Next Steps

Once running successfully:

1. **Test APIs** - See `TESTING_GUIDE.md`
2. **Try WebSocket** - http://localhost:8080/index.html
3. **Use Postman** - Import the collection
4. **Read the code** - See `STEP_BY_STEP_WALKTHROUGH.md`

---

## 💡 Pro Tips

- **Keep VS Code open** while testing
- **Watch the terminal** for logs and errors
- **Use Ctrl + ` ** to toggle terminal visibility
- **Use Ctrl + B** to toggle sidebar
- **First run is slow** - be patient!

---

**You're all set! Happy coding! 🚀**

