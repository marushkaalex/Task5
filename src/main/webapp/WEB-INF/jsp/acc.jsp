<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/static/style/css/style.css"/>"/>
<head>
    <title>User account</title>
</head>
<body>
<div id="wrapper">
    <%--TODO use locale--%>
    <m:sidebar user="${user}"/>
    <div class="container">
        <a href="${pageContext.request.contextPath}">HOME</a>

        <h1>${sessionScope.user.username}</h1><br>

        <form action="${pageContext.request.contextPath}/acc" method="post" id="options">
            <label>
                <select required name="lang" form="options">
                    <option value="en" <c:if test="${sessionScope.lang == en}">select="select"</c:if>>English</option>
                    <option value="ru" <c:if test="${sessionScope.lang == ru}">select="select"</c:if>>Russian</option>
                </select>
            </label><br>
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
