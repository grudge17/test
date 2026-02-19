# 🚀 How to Run the Application - Complete Guide

## ⚠️ IMPORTANT: Java 17 Required

Before running, you **MUST** have Java 17 installed. See `INSTALLATION_GUIDE.md` for installation instructions.

---

## 🎯 Three Ways to Run

### **Method 1: Using VS Code (EASIEST - Recommended!)**

This is the **easiest** method and doesn't require Maven installation!

#### **Step-by-Step:**

1. **Install Java 17** (if not already installed)
   - See `INSTALLATION_GUIDE.md`
   - Verify: Open PowerShell and run `java -version`

2. **Open VS Code**
   - Launch Visual Studio Code

3. **Open the Project**
   - Click "File" → "Open Folder"
   - Navigate to `c:\test`
   - Click "Select Folder"

4. **Wait for Java Extension to Load**
   - Look at the bottom-right corner of VS Code
   - You'll see "Java" and a loading indicator
   - Wait until it says "Ready" (may take 1-2 minutes first time)

5. **Find the Main Class**
   - In the Explorer panel (left side)
   - Navigate to: `src/main/java/com/insurance/claims/`
   - Click on `ClaimsProcessingApplication.java`

6. **Run the Application**
   
   **Option A: Using the Run Button**
   - Look for the "Run" button above the `main` method
   - It looks like a green play button ▶️
   - Click it!

   **Option B: Using Right-Click**
   - Right-click anywhere in the file
   - Select "Run Java"

   **Option C: Using Command Palette**
   - Press `Ctrl + Shift + P`
   - Type: "Java: Run Java"
   - Press Enter

7. **Check the Terminal**
   - A terminal will open at the bottom
   - You should see:
   ```
   ==============================================
   Claims Processing System Started Successfully!
   ==============================================
   Sample Data Initialized:
   Users: john_doe (ID: 1), jane_smith (ID: 2)
   Admin: admin (ID: 3)
   ==============================================
   ```

8. **✅ Success!**
   - Application is running at: http://localhost:8080
   - Keep VS Code open (don't close the terminal)

---

### **Method 2: Using Maven Wrapper (No Maven Installation Needed)**

If VS Code doesn't work, use this method:

1. **Open PowerShell**
   - Press `Win + X`
   - Select "Windows PowerShell"

2. **Navigate to Project**
   ```powershell
   cd c:\test
   ```

3. **Run the Application**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

4. **Wait for Startup**
   - First time will download Maven and dependencies (may take 5-10 minutes)
   - Subsequent runs will be faster

5. **Look for Success Message**
   ```
   ==============================================
   Claims Processing System Started Successfully!
   ==============================================
   ```

---

### **Method 3: Using Maven (If You Have Maven Installed)**

1. **Open PowerShell**
   ```powershell
   cd c:\test
   ```

2. **Build the Project**
   ```powershell
   mvn clean install
   ```

3. **Run the Application**
   ```powershell
   mvn spring-boot:run
   ```

---

## ✅ How to Know It's Running

### **Success Indicators:**

1. **Console Output:**
   ```
   ==============================================
   Claims Processing System Started Successfully!
   ==============================================
   Sample Data Initialized:
   Users: john_doe (ID: 1), jane_smith (ID: 2)
   Admin: admin (ID: 3)
   ==============================================
   ```

2. **Last Line Should Say:**
   ```
   Started ClaimsProcessingApplication in X.XXX seconds
   ```

3. **Test in Browser:**
   - Open browser
   - Go to: http://localhost:8080/api/users
   - You should see JSON with user data

---

## 🧪 Quick Test After Running

### **Test 1: Browser Test**
Open browser and visit:
```
http://localhost:8080/api/users
```

**Expected:** JSON array with 3 users

### **Test 2: PowerShell Test**
Open a **NEW** PowerShell window:
```powershell
curl http://localhost:8080/api/users
```

### **Test 3: WebSocket Test**
Open browser:
```
http://localhost:8080/index.html
```
- Enter User ID: 1
- Click "Connect"
- Should show "Connected"

---

## 🛑 How to Stop the Application

### **In VS Code:**
- Click the trash can icon 🗑️ in the terminal
- Or press `Ctrl + C` in the terminal

### **In PowerShell:**
- Press `Ctrl + C`
- Type `Y` and press Enter

---

## 🐛 Troubleshooting

### **"java is not recognized"**
❌ **Problem:** Java not installed or not in PATH

✅ **Solution:**
1. Install Java 17 (see `INSTALLATION_GUIDE.md`)
2. Open a **NEW** PowerShell window
3. Verify: `java -version`

---

### **"Port 8080 already in use"**
❌ **Problem:** Another application is using port 8080

✅ **Solution 1:** Stop the other application

✅ **Solution 2:** Change port
1. Open `src/main/resources/application.properties`
2. Add line: `server.port=8081`
3. Save and restart
4. Access at: http://localhost:8081

---

### **VS Code "Run" button not showing**
❌ **Problem:** Java extension not loaded

✅ **Solution:**
1. Press `Ctrl + Shift + P`
2. Type: "Java: Clean Java Language Server Workspace"
3. Select it and press Enter
4. Restart VS Code
5. Wait for Java extension to load

---

### **"Cannot find symbol" or compilation errors**
❌ **Problem:** Dependencies not downloaded

✅ **Solution:**
1. Press `Ctrl + Shift + P`
2. Type: "Java: Clean Workspace"
3. Restart VS Code
4. Or use Maven: `mvn clean install`

---

### **Application starts but APIs don't work**
❌ **Problem:** Application crashed after startup

✅ **Solution:**
1. Check the terminal for error messages
2. Look for red text or stack traces
3. Common issues:
   - Database connection error
   - Port already in use
   - Missing dependencies

---

## 📋 Pre-Flight Checklist

Before running, make sure:

- [ ] Java 17 is installed: `java -version`
- [ ] VS Code is installed
- [ ] Extension Pack for Java is installed in VS Code
- [ ] Project folder `c:\test` is opened in VS Code
- [ ] No other application is using port 8080

---

## 🎯 What to Do After Running

Once the application is running:

1. **Test the APIs** - See `TESTING_GUIDE.md`
2. **Try the WebSocket** - Open http://localhost:8080/index.html
3. **Use Postman** - Import `Claims-Processing-API.postman_collection.json`
4. **Read the code** - See `STEP_BY_STEP_WALKTHROUGH.md`

---

## 💡 Tips

- **Keep the terminal open** - Closing it stops the application
- **Watch the logs** - They show what's happening
- **First run is slow** - Dependencies need to download
- **Subsequent runs are fast** - Usually starts in 5-10 seconds

---

## 📞 Need Help?

If you're stuck:

1. Check the error message in the terminal
2. Read the troubleshooting section above
3. Make sure Java 17 is installed: `java -version`
4. Try restarting VS Code
5. Try the Maven wrapper method instead

---

## 🎉 Success!

Once you see the success message, you're ready to test the application!

**Next Steps:**
1. Open browser: http://localhost:8080/api/users
2. See the testing guide: `TESTING_GUIDE.md`
3. Try the WebSocket demo: http://localhost:8080/index.html

---

**Happy Coding! 🚀**

