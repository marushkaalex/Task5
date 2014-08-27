<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" action="upload" enctype="multipart/form-data">
    <input class="form-control" name="name" type="text" placeholder="<fmt:message key="upload.name"/>">
    <input class="form-control" name="description" type="text" placeholder="<fmt:message key="upload.description"/>">
    <input class="form-control" name="imageFile" type="file" accept="image/*">
    <input type="submit">
</form>
</body>
</html>
