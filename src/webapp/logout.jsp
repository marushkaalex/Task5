<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Refresh" content="3;url=index.jsp">
<head>
    <title></title>
</head>
<body>
<%
    session.removeAttribute("username");
    session.removeAttribute("password");
    session.invalidate();
%>
<h1>Logout was done successfully!</h1>
</body>
</html>
