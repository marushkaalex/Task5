<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="../static/style/css/style.css" media="screen" type="text/css"/>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration Form</h1>

<form action="${pageContext.request.contextPath}/checkRegister" method="post">
    <label>
        Username:
        <input type="text"><br>
    </label>
    <label>
        Email:
        <input type="text"><br>
    </label>
    <label>
        Password:
        <input type="password"><br>
    </label>
    <input type="submit"><br>
</form>
<c:set var="errorArray" scope="page" value="${requestScope.errorArray}"/>
<c:if test="${errorArray != null}">
    <h2>There are some errors</h2>
    <ul>
        <c:forEach var="i" begin="0" end="${errorArray.length} - 1">
            <li><c:out value="${errorArray[i]}"/></li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
