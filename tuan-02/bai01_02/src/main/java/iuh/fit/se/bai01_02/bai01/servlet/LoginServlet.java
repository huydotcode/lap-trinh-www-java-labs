package iuh.fit.se.bai01_02.bai01.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Login</h1>");
        writer.println("<p>Login Success with: " + username + "</p>");
        writer.println("</body></html>");
        writer.close();
    }
}
