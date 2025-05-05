# Program 20 - JSP with web.xml Configuration

## Description
Form handling using JSPs with deployment descriptor.

## Files
- `WEB-INF/web.xml`: Servlet mapping
- `form.jsp`: Input form
- `submit.jsp`: Handles form data

## Instructions
1. Place `program20` folder inside `C:\apache-tomcat-9.0\webapps`.

2. Ensure `web.xml` is properly configured for any servlets if needed.

3. Start Tomcat:
```cmd
cd C:\apache-tomcat-9.0\bin
startup.bat
```

4. Access the application:
```
http://localhost:8080/program20/form.jsp
```
Submit to navigate to `submit.jsp`.