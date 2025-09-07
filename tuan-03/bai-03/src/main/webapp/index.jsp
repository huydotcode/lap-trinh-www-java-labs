<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
    <title>Multi-language</title>
</head>
<body>

<c:set var="languageCode" value="${param.radLanguageCode}"/>
<c:if test="${not empty languageCode}">
    <fmt:setLocale value="${languageCode}" scope="session"/>
</c:if>

<fmt:setBundle basename="language" scope="session"/>

<form action="index.jsp" method="POST">

    <fmt:message key="lang.choose"/>

    <input type="radio" name="radLanguageCode" value="vi"
           <c:if test="${languageCode == 'vi'}">checked</c:if> />
    <fmt:message key="lang.vn"/>

    <input type="radio" name="radLanguageCode" value="en"
           <c:if test="${languageCode == 'en'}">checked</c:if> />
    <fmt:message key="lang.en"/>

    <input type="submit" value="<fmt:message key='lang.choose'/>"/>

    <table border="0">
        <tr>
            <td><fmt:message key="login.username"/></td>
            <td><input type="text" name="txtUserName"/></td>
        </tr>
        <tr>
            <td><fmt:message key="login.password"/></td>
            <td><input type="password" name="txtPassword"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<fmt:message key='login.button'/>"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
