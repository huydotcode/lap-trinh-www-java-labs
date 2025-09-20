package iuh.fit.se.bai02validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.Set;

public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward to index.jsp for displaying the form
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Tạo ValidatorFactory và Validator
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            // Lấy parameters từ form
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String country = req.getParameter("country");

            // Tạo User object
            User user = new User(name, email, country);

            // Validate user object
            Set<ConstraintViolation<User>> violations = validator.validate(user);

            if (!violations.isEmpty()) {
                // Có lỗi validation - hiển thị lỗi
                req.setAttribute("violations", violations);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                // Validation thành công
                req.setAttribute("message", "Register successful!");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            // Fallback nếu có lỗi với Bean Validation
            req.setAttribute("message", "Error: " + e.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}