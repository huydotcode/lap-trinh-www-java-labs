<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 8/24/2025
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<form action="login" method="post" name="login-form">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <br>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <br>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <br>

    <input type="submit" value="Login">
</form>

</body>
</html>
