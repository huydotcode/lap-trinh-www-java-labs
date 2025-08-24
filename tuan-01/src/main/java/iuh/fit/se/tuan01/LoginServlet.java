package iuh.fit.se.tuan01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Login Get</h1>");
        writer.println("<p>Username: " + username + "</p>");
        writer.println("<p>Name: " + name + "</p>");
        writer.println("<p>Email: " + email + "</p>");
        writer.println("</body></html>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Login Post</h1>");
        writer.println("<p>Username: " + username + "</p>");
        writer.println("<p>Name: " + name + "</p>");
        writer.println("<p>Email: " + email + "</p>");
        writer.println("</body></html>");

        writer.close();
    }
}

