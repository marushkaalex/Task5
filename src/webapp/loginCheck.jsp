<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Check</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if ((username.equals("username")) && password.equals("password")) {
        session.setAttribute("username", username);
        response.sendRedirect("Home.jsp");
    }
    else {
        response.sendRedirect("Error.jsp");
    }
%>
</body>
</html>
