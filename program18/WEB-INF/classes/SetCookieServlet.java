
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetCookieServlet")
public class SetCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie userCookie = new Cookie("username", "AdminUser");
        userCookie.setMaxAge(60 * 60 * 24); // 1 day
        response.addCookie(userCookie);

        out.println("<h3>Cookie 'username' has been set!</h3>");
        out.println("<a href='GetCookieServlet'>Click here to retrieve the cookie</a>");
        out.println("<h3>Abhay Raj, 00976803122</h3>");
    }
}
