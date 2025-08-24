package iuh.fit.se.tuan01;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.se.tuan01.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "userServlet",
        urlPatterns = {"/user"}
)
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        User user = new User("Huy", "ngonhuthuy1234@gmail.com");

        String json = mapper.writeValueAsString(user);

        writer.write(json);
        writer.flush();
        writer.close();
    }
}
