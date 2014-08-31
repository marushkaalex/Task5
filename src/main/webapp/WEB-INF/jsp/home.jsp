<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<html>
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/static/style/css/style.css"/>"/>
<head>
    <title></title>
</head>
<body>
<div id="wrapper"/>
<m:sidebar user="${user}"/>
<div class="container">
    <div style="align-self: center">
        <h2>
            <fmt:message key="label.hello"/>, <a href="<c:url value="/acc"/>">${sessionScope.user.username}</a>
        </h2>
        <a href="<c:url value="/logout"/>"><fmt:message key="label.logout"/></a><br>

        <a href="<c:url value="/artists"/>">artists list</a><br>

        <c:if test="${sessionScope.user.role == 'ARTIST'}">
            <a href="<c:url value="/gallery"/>">Gallery</a><br>
        </c:if>
    </div>
</div>
</div>
</body>
</html>
