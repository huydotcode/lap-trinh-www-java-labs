<%@ page import="iuh.fit.se.bai01.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Submit</title>
</head>
<body>

<%
    Student student = (Student) request.getAttribute("student");
    if (student != null) {
        out.println(
                "Firstname: " + student.getFirstName()
                        + "<br/> Last name: " + student.getLastName()
                        + "<br/> Email: " + student.getEmail()
                        + "<br/> Phone number: " + student.getPhoneNumber()
                        + "<br/> Birthday: " + student.getDateOfBirth()
        );
    } else {
        out.println(
                "Khong tim thay student"
        );
    }

%>

</body>
</html>
