<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="static/style/css/style.css">
<head>
    <title></title>
</head>
<body>
<div style="align-self: center">
    <h2>
        <%
            String a = session.getAttribute("username").toString();
            out.println("Hello " + a);
        %>
    </h2>
    <a href="logout.jsp">Logout</a>
</div>

</body>
</html>
