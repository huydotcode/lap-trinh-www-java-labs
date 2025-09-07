package iuh.fit.se.bai01;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/registration-form")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get basic info
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String dobDay = req.getParameter("dob-day");
        String dobMonth = req.getParameter("dob-month");
        String dobYear = req.getParameter("dob-year");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        Gender gender = Objects.equals(req.getParameter("gender"), "Male") ? Gender.MALE : Gender.FEMALE;
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String pincode = req.getParameter("pincode");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String course = req.getParameter("course");

        String birthday = dobDay + "-" + dobMonth + "-" + dobYear;

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDateOfBirth(birthday);
        student.setEmail(email);
        student.setPhoneNumber(mobile);
        student.setGender(gender);
        student.setAddress(address);
        student.setCity(city);
        student.setPinCode(Integer.parseInt(pincode));
        student.setState(state);
        student.setCountry(country);
        student.setCourseAppliesFor(course);


        req.setAttribute("student", student);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/result-form.jsp");
        requestDispatcher.forward(req, resp);
    }
}
