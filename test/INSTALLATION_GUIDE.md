# 🔧 Installation Guide - Java 17 Setup

## ⚠️ Java 17 Not Found

Your system doesn't have Java 17 installed or it's not in your PATH. Let's fix this!

## 📥 Step 1: Install Java 17

### **Option A: Using Microsoft Build of OpenJDK (Recommended for Windows)**

1. **Download Java 17:**
   - Go to: https://learn.microsoft.com/en-us/java/openjdk/download
   - Download: **Microsoft Build of OpenJDK 17 (LTS)** for Windows x64
   - File: `microsoft-jdk-17.x.x-windows-x64.msi`

2. **Install:**
   - Run the downloaded `.msi` file
   - Click "Next" through the installer
   - ✅ **IMPORTANT:** Check "Add to PATH" option
   - Click "Install"
   - Wait for installation to complete

3. **Verify Installation:**
   - Open a **NEW** PowerShell window
   - Run: `java -version`
   - You should see: `openjdk version "17.x.x"`

### **Option B: Using Eclipse Temurin (Alternative)**

1. **Download:**
   - Go to: https://adoptium.net/temurin/releases/
   - Select:
     - Version: **17 - LTS**
     - Operating System: **Windows**
     - Architecture: **x64**
   - Click "Download .msi"

2. **Install:**
   - Run the `.msi` installer
   - ✅ **IMPORTANT:** Select "Set JAVA_HOME variable"
   - ✅ **IMPORTANT:** Select "Add to PATH"
   - Complete installation

3. **Verify:**
   - Open **NEW** PowerShell
   - Run: `java -version`

### **Option C: Using Chocolatey (If you have Chocolatey)**

```powershell
choco install openjdk17
```

---

## 🔄 Step 2: Verify Java Installation

After installing Java, open a **NEW** PowerShell window and run:

```powershell
java -version
```

**Expected Output:**
```
openjdk version "17.0.x" 2024-xx-xx
OpenJDK Runtime Environment (build 17.0.x+x)
OpenJDK 64-Bit Server VM (build 17.0.x+x, mixed mode, sharing)
```

Also check:
```powershell
javac -version
```

**Expected Output:**
```
javac 17.0.x
```

---

## 🛠️ Step 3: Install Maven (Optional but Recommended)

### **Option A: Using Chocolatey**
```powershell
choco install maven
```

### **Option B: Manual Installation**

1. **Download:**
   - Go to: https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.x-bin.zip`

2. **Extract:**
   - Extract to: `C:\Program Files\Apache\maven`

3. **Add to PATH:**
   - Search "Environment Variables" in Windows
   - Click "Environment Variables"
   - Under "System Variables", find "Path"
   - Click "Edit" → "New"
   - Add: `C:\Program Files\Apache\maven\bin`
   - Click "OK"

4. **Verify:**
   - Open **NEW** PowerShell
   - Run: `mvn -version`

---

## 🚀 Step 4: Run the Application

Once Java 17 is installed, you have **3 options**:

### **Option 1: Using VS Code (Easiest - No Maven needed!)**

1. **Open VS Code**
2. **Open Folder:** `c:\test`
3. **Wait for Java Extension to activate** (bottom right corner)
4. **Two ways to run:**

   **Method A: Using Run Button**
   - Find `ClaimsProcessingApplication.java` in Explorer
   - Look for the "Run" button above the `main` method
   - Click "Run"

   **Method B: Using Command Palette**
   - Press `Ctrl + Shift + P`
   - Type: "Java: Run"
   - Select the main class

5. **Check Terminal** - You should see:
   ```
   ==============================================
   Claims Processing System Started Successfully!
   ==============================================
   ```

### **Option 2: Using Maven (If installed)**

```powershell
cd c:\test
mvn clean install
mvn spring-boot:run
```

### **Option 3: Using Maven Wrapper (No Maven installation needed)**

First, let me create the Maven wrapper for you...

---

## ✅ Verification Checklist

After installation, verify:

- [ ] Java 17 is installed: `java -version`
- [ ] JAVA_HOME is set (optional): `echo $env:JAVA_HOME`
- [ ] VS Code Java Extension is active
- [ ] Can see Java projects in VS Code

---

## 🐛 Troubleshooting

### **"java is not recognized"**
- Make sure you opened a **NEW** PowerShell window after installation
- Verify Java is in PATH: `echo $env:PATH`
- Restart VS Code

### **"JAVA_HOME not set"**
1. Search "Environment Variables" in Windows
2. Click "Environment Variables"
3. Under "System Variables", click "New"
4. Variable name: `JAVA_HOME`
5. Variable value: `C:\Program Files\Microsoft\jdk-17.x.x` (your Java installation path)
6. Click "OK"

### **VS Code not detecting Java**
1. Press `Ctrl + Shift + P`
2. Type: "Java: Clean Java Language Server Workspace"
3. Restart VS Code

---

## 📞 Next Steps

1. **Install Java 17** using one of the options above
2. **Verify installation** with `java -version`
3. **Open VS Code** and open the `c:\test` folder
4. **Run the application** using VS Code's Run button
5. **Test the APIs** using the testing guide

---

## 💡 Quick Links

- **Microsoft OpenJDK:** https://learn.microsoft.com/en-us/java/openjdk/download
- **Eclipse Temurin:** https://adoptium.net/
- **Maven Download:** https://maven.apache.org/download.cgi
- **VS Code Java Extension:** https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack

---

**Once Java is installed, come back and we'll run the application together!** 🚀

