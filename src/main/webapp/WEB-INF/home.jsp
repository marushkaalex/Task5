<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<html>
<link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.css"/>
<head>
    <title></title>
</head>
<body>
<div style="align-self: center">
    <h2>
        <fmt:message key="label.hello"/>
        , <a href="acc">${sessionScope.user.username}</a>
    </h2>
    <a href="logout"><fmt:message key="label.logout"/></a>

    <form method="post" action="upload" enctype="multipart/form-data">
        <input class="form-control" name="imageFile" type="file" accept="image/*">
        <input type="submit">
    </form>
</div>

</body>
</html>
