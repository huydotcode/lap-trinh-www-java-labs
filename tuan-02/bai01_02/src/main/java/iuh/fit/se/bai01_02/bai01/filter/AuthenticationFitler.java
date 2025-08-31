package iuh.fit.se.bai01_02.bai01.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFitler extends HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");

        String usernameFilter = this.getFilterConfig().getInitParameter("username");
        String passwordFilter = this.getFilterConfig().getInitParameter("password");

        if (username.equals(usernameFilter) && password.equals(passwordFilter)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter out = servletResponse.getWriter();
            out.println("<html><body>");
            out.println("<h1>Login</h1>");
            out.println("<p>Login Failed</p>");
            out.println("</body></html>");
            out.close();
        }
    }
}
