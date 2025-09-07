package iuh.fit.se.bai02;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(
        name = "registerServlet",
        urlPatterns = "/register"
)
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String reEmail = request.getParameter("reEmail");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");

        // Birthday tách từ 3 select box
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String year = request.getParameter("year");

        // Validate email nhập lại
        if (email == null || !email.equals(reEmail)) {
            request.setAttribute("error", "Email nhập lại không khớp!");
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }

        // Chuyển birthday sang LocalDate
        LocalDate birthday = null;
        try {
            birthday = LocalDate.of(
                    Integer.parseInt(year),
                    Integer.parseInt(month),
                    Integer.parseInt(day)
            );
        } catch (Exception e) {
            request.setAttribute("error", "Ngày sinh không hợp lệ!");
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }

        // Tạo User mới
        User user = new User(firstName, lastName, email, password, birthday, gender);

        // Lưu vào danh sách (UserDAO)
        UserDAO.addUser(user);

        // Gửi danh sách sang list.jsp
        request.setAttribute("users", UserDAO.getAllUsers());
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        rd.forward(request, response);
    }
}
