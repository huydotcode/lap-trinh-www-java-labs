package iuh.fit.se.tuan01;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Hello" + "</h1>");
        out.println("<h1> Param trong Servlet " + this.getServletConfig().getInitParameter("username") + "</h1>");
        out.println("<h1> Param trong App " + this.getServletContext().getInitParameter("appName") + "</h1>");
        out.println("</body></html>");
    }
}
