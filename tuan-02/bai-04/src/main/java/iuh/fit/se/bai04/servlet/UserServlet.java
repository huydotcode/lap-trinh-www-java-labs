package iuh.fit.se.bai04.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.se.bai04.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "userServlet",
        urlPatterns = "/users"
)
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId("USER01");
        user.setName("Huy");
        user.setAge(22);
        user.setEmail("ngohuy@gmail.com");

        // Trả về json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(json);
    }
}
