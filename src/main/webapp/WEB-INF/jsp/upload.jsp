<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<%--<fmt:requestEncoding value="UTF-8"/>--%>

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
        <form method="post" action="upload" enctype="multipart/form-data">
            <input class="form-control" name="name" type="text" placeholder="<fmt:message key="upload.name"/>">
            <input class="form-control" name="description" type="text"
                   placeholder="<fmt:message key="upload.description"/>">
            <input class="form-control" name="imageFile" type="file" accept="image/*">
            <input type="submit">
        </form>
    </div>
</div>
</div>
</body>
</html>
