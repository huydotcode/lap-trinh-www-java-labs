<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="iuh.fit.se.bai02validation.User" %>
<%@ page import="jakarta.validation.ConstraintViolation" %>
<%@ page import="java.util.Set" %>
<%--<%@ page import="jakarta.validation.ConstraintViolation" %>--%>
<%--<%@ page import="java.util.Set" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input[type="text"]:focus, input[type="email"]:focus {
            outline: none;
            border-color: #4caf50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.3);
        }
        .btn {
            background-color: #4caf50;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .success {
            color: #4caf50;
            font-size: 16px;
            text-align: center;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #e8f5e8;
            border-radius: 5px;
        }
        .error-list {
            background-color: #ffebee;
            border: 1px solid #f44336;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 20px;
        }
        .error-list h3 {
            color: #d32f2f;
            margin-top: 0;
        }
        .error-list ul {
            margin: 10px 0;
            padding-left: 20px;
        }
        .error-list li {
            color: #d32f2f;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Registration Form</h1>
        
        <%-- Hiển thị thông báo thành công --%>
        <% if (request.getAttribute("message") != null) { %>
            <div class="success">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
        
        <%-- Hiển thị lỗi Bean Validation --%>
        <%
            Set<ConstraintViolation<User>> violations = (Set<ConstraintViolation<User>>) request.getAttribute("violations");
            if (violations != null && !violations.isEmpty()) {
        %>
            <div class="error-list">
                <h3>Validation Errors:</h3>
                <ul>
                    <% for (ConstraintViolation<User> violation : violations) { %>
                        <li><%= violation.getMessage() %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>
        
        <form method="post" action="user-servlet">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" 
                       value="<%= request.getAttribute("user") != null ? ((User)request.getAttribute("user")).getName() : "" %>"
                       placeholder="Enter your name (8-50 characters)" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" 
                       value="<%= request.getAttribute("user") != null ? ((User)request.getAttribute("user")).getEmail() : "" %>"
                       placeholder="Enter your email address" required>
            </div>
            
            <div class="form-group">
                <label for="country">Country:</label>
                <input type="text" id="country" name="country" 
                       value="<%= request.getAttribute("user") != null ? ((User)request.getAttribute("user")).getCountry() : "" %>"
                       placeholder="Enter your country">
            </div>
            
            <button type="submit" class="btn">Register</button>
        </form>
        
        <br/>
        <a href="hello-servlet">Go to Hello Servlet</a>
    </div>
</body>
</html>