<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <a href="<c:url value="/home"/>">home</a><br>
        <c:if test="${upload == true}">
        <a href="<c:url value="/upload"/>">Upload image</a>
        </c:if>
        <form action="${pageContext.request.contextPath}/deletePainting" method="post">
            <c:forEach var="painting" items="${gallery.links}">
                <h3>${painting.key.name}</h3><br>
                <img class="img-thumbnail" style="height: 170px; width: auto; max-width: 500px"
                     src="<c:out value="${painting.value}"/>"/>
                <a href="deletePainting"><input name="id" value="${painting.key.id}" type="hidden">
                    <button value="${painting.key.path}" type="submit" name="path">Delete</button>
                </a><br>
                <h5>${painting.key.description}</h5>
            </c:forEach>
        </form>
    </div>
</div>
</body>
</html>
