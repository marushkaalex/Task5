<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.css"/>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/deletePainting" method="post">
    <c:forEach var="painting" items="${gallery.links}">
        <h3>${painting.key.name}</h3><br>
        <img class="img-thumbnail" src="<c:out value="${painting.value}"/>"/>
        <a href="deletePainting"><input name="id" value="${painting.key.id}" type="hidden">
            <button value="${painting.key.path}" type="submit" name="path">Delete</button>
        </a><br>
        <h5>${painting.key.description}</h5>
    </c:forEach>
</form>
</body>
</html>
