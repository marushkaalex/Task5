<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<html>
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/static/style/css/style.css"/>"/>
<head>
    <title></title>
</head>
<body>
<div id="wrapper">
    <m:sidebar user="${user}"/>
    <div class="container">
        <c:forEach items="${artists}" var="artist">
            <a href="<c:url value="/user/${artist.username}"/>"><c:out value="${artist.username}"/></a><br>
        </c:forEach>
    </div>
</div>
</body>
</html>
