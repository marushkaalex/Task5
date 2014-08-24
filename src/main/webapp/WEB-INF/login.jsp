<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Login Form - CodePen</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/css/style.css">
</head>
<body>
<span href="#" class="button" id="toggle-login">Log in</span>

<div id="login">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" placeholder="<fmt:message key="label.username"/>" name="username" required autofocus/>
        <input type="password" placeholder="<fmt:message key="label.password"/>" name="password" required/>
        <input type="submit" value="Submit"/>
    </form>
</div>

<c:if test="${requestScope.error != null}">
    <fmt:message key="login.user_not_found"/>
</c:if>

</body>

</html>
