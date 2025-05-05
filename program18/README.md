# Program 18 - Cookie Servlet Setup

## Description
This program demonstrates setting and getting cookies using servlets.

## Files
- `SetCookieServlet.java`
- `GetCookieServlet.java`

## Instructions
1. **Compile Servlets:**
```cmd
cd program18\WEB-INF\classes
javac -cp "C:\apache-tomcat-9.0\lib\servlet-api.jar" SetCookieServlet.java GetCookieServlet.java
```

2. **Move Class Files:**
The compiled class files should already be in the `classes` directory under `WEB-INF`.

3. **Deploy and Run:**
- Place the `program18` folder inside `C:\apache-tomcat-9.0\webapps`
- Start Tomcat:
```cmd
cd C:\apache-tomcat-9.0\bin
startup.bat
```
- Access via browser:
  - `http://localhost:8080/program18/SetCookieServlet`
  - `http://localhost:8080/program18/GetCookieServlet`