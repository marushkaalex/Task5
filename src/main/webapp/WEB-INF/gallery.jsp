<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/deletePainting" method="post">
    <c:forEach var="painting" items="${gallery.links}">
        <img src="<c:out value="${painting.value}"/>"/>
        <a href="deletePainting"><input name="id" value="${painting.key.id}" type="hidden">
            <button value="${painting.key.path}" type="submit" name="path">Delete</button>
        </a><br>
    </c:forEach>
</form>
</body>
</html>
