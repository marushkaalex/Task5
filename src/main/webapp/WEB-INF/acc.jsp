<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 24.08.2014
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/css/style.css">
<head>
    <title>User account</title>
</head>
<body>
<%--TODO use locale--%>
<a href="${pageContext.request.contextPath}">HOME</a>

<h1>${sessionScope.user.username}</h1><br>

<form action="${pageContext.request.contextPath}/acc" method="post">
    <label>
        <select required name="language">
            <option value="en">English</option>
            <option value="ru">Russian</option>
        </select>
    </label><br>
    <input type="submit">
</form>
</body>
</html>
