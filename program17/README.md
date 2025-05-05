# Setup JAVA_HOME and Run Apache Tomcat on Windows

## 1. Prerequisites
- Java JDK installed (e.g., `C:\Program Files\Java\jdk-21`)
- Apache Tomcat extracted (e.g., `C:\apache-tomcat-9.0`)
- Servlet source file (e.g., `UserValidationServlet.java`)

## 2. Set JAVA_HOME Permanently
1. Open **System Properties > Environment Variables**
2. Under **System variables**, click **New**:
   - Name: `JAVA_HOME`
   - Value: `C:\Program Files\Java\jdk-21`
3. Edit **Path**, click **New**, and add:
   ```
   %JAVA_HOME%\bin
   ```

## 3. Verify Setup
Open a new terminal and run:
```cmd
echo %JAVA_HOME%
java -version
```

## 4. Compile Servlet
Assuming:
- Tomcat is in `C:\apache-tomcat-9.0`
- Servlet is in `C:\MyWebApp\src\UserValidationServlet.java`
- Web app is in `C:\MyWebApp`

Run in Command Prompt or PowerShell:
```cmd
cd C:\MyWebApp\src
javac -cp "C:\apache-tomcat-9.0\lib\servlet-api.jar" -d ..\WEB-INF\classes UserValidationServlet.java
```

## 5. Deploy Servlet
Ensure the directory structure:
```
C:\MyWebApp
│
├── WEB-INF
│   ├── web.xml
│   └── classes
│       └── UserValidationServlet.class
```

Copy `MyWebApp` to Tomcat's `webapps`:
```cmd
xcopy /E /I C:\MyWebApp C:\apache-tomcat-9.0\webapps\MyWebApp
```

## 6. Start Tomcat
```cmd
cd C:\apache-tomcat-9.0\bin
startup.bat
```

Visit: [http://localhost:8080/MyWebApp/UserValidationServlet](http://localhost:8080/MyWebApp/UserValidationServlet)

## 7. Optional: Temporary JAVA_HOME
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
cd C:\apache-tomcat-9.0\bin
.\startup.bat
```