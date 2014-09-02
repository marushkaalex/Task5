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
        Recently added
        <div class="row">
            <m:show_gallery paintings="${recentlyAdded}"/>
        </div>
    </div>
</div>
</body>
</html>
