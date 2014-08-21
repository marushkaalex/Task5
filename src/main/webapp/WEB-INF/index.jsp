<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/css/style.css">
<head>
    <title>Hello World</title>
</head>
<body>
<div style="font-size: 10em; text-align: center">
    <%
        //        response.sendRedirect(request.getContextPath() + "/do?action=login");
    %>
    Please <a href="${pageContext.request.contextPath}/login">log in</a> <br>
    or <a href="${pageContext.request.contextPath}/register">register.</a>
</div>
</body>
</html>
