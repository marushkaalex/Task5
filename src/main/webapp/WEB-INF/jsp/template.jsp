<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>

<html>
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/static/style/css/style.css"/>"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
<div id="wrapper">
    <m:sidebar user="${user}"/>
    <div class="container">
    </div>
</div>
</div>
</body>
</html>
