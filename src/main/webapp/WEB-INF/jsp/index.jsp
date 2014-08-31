<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="i18n"/>

<html>
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/static/style/css/style.css"/>"/>
<head>
    <title>Hello World</title>
</head>
<body>
<div id="wrapper">
    <m:sidebar user="${user}"/>
    <div class="container">
        <c:if test="${requestScope.getLocale != null}">not null</c:if>
        <div style="font-size: 10em; text-align: center">
            <fmt:message key="label.hello"/>. Please <a href="${pageContext.request.contextPath}/login">log in</a> <br>
            or <a href="${pageContext.request.contextPath}/register">register.</a>
        </div>
    </div>
</div>
</body>
</html>
