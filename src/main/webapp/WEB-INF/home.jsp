<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <a href="logout"><fmt:message key="label.logout"/></a><br>

    <c:if test="${sessionScope.user.role == 'ARTIST'}">
        <a href="upload">Upload image</a>
    </c:if>

    <br><img src="${pageContext.request.contextPath}/img/asdf"/>

</div>

</body>
</html>
