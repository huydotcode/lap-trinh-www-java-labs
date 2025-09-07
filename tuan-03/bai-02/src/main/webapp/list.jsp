<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="iuh.fit.se.bai02.User" %>

<html>
<head>
    <title>Danh sách tài khoản</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: auto; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #f2f2f2; }
        h2 { text-align: center; }
    </style>
</head>
<body>
<h2>Danh sách tài khoản đã đăng ký</h2>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Birthday</th>
        <th>Gender</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%= u.getFirstName() %></td>
        <td><%= u.getLastName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getBirthday() %></td>
        <td><%= u.getGender() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
<br/>
<div style="text-align:center;">
    <a href="index.jsp">Đăng ký</a>
</div>
</body>
</html>
