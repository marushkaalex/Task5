<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="static/style/css/style.css">
<head>
    <title>Hello World</title>
</head>
<body>
<div style="font-size: 10em; text-align: center">
    <%
        //        response.sendRedirect(request.getContextPath() + "/do?action=login");
    %>
    Please <a href="${pageContext.request.contextPath}/do/login">log in</a> <br>
    or <a href="${pageContext.request.contextPath}/do/register">register.</a>
</div>
</body>
</html>
