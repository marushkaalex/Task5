<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="static/style/css/style.css" media="screen" type="text/css"/>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration Form</h1>

<form action="checkRegister" method="post">
    <label>
        Username:
        <input type="text" name="username"><br>
    </label>
    <label>
        Email:
        <input type="email" name="email"><br>
    </label>
    <label>
        Password:
        <input type="password" name="password"><br>
    </label>
    <input type="submit"><br>
</form>
<c:set var="errorList" scope="page" value="${sessionScope.register_errorList}"/>
<c:out value="${errorList}"/>
<c:if test="${errorList != null || errorList.size() == 0}">
    <h2>There are some errors</h2>
    <ul>
        <c:forEach var="i" begin="0" end="${errorList.size() - 1}">
            <li><c:out value="${errorList.get(i)}"/></li>
        </c:forEach>
    </ul>
</c:if>
<%
    session.removeAttribute("register_errorList");
%>
</body>
</html>
