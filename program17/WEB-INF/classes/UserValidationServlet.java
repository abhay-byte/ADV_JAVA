import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserValidationServlet")
public class UserValidationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded validation (replace with database validation in real applications)
        if ("admin".equals(username) && "1234".equals(password)) {
            out.println("<h3>Login Successful!</h3>");
            out.println("<p>Welcome, " + username + "!</p>");
	out.println("<h3>Abhay Raj, 00976803122</h3>");
        } else {
            out.println("<h3>Invalid Credentials</h3>");
            out.println("<a href='index.html'>Try Again</a>");
	out.println("<h3>Abhay Raj, 00976803122</h3>");
        }

        out.close();
    }
}