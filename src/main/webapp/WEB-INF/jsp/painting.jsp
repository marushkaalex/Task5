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
        <div class="row">
            <c:forEach var="painting" items="${gallery.links}">
            <h2><c:out value="${painting.key.name}"/></h2>

        </div>
        <div class="row">
            <a href="<c:url value="/user/${artist.username}"/>"><c:out value="${artist.username}"/></a>
        </div>
        <div class="row">
            <img class="img-thumbnail center" style="position: relative;
	 max-height: 500px; width: auto; max-width: 800px;"
                 src="<c:out value="${painting.value}"/>"/>
        </div>
        <div class="row">
            <c:out value="${painting.key.likes}"/>
        </div>
        <c:if test="${not empty user}">
            <div class="row">
                <form action="<c:url value="/like"/>" method="post">
                    <button type="submit" name="like" value="${painting.key.id}"><c:out value="${isLiked}"/></button>
                    <c:if test="${user.id == painting.key.artistId}">
                        <a href="<c:url value="/deletePainting"/>"><input name="id" value="${painting.key.id}"
                                                                          type="hidden">
                            <button value="${painting.key.path}" type="submit" name="path">Delete</button>
                        </a><br>
                    </c:if>
                </form>
            </div>
        </c:if>
        </c:forEach>
    </div>
</div>
</div>
</body>
</html>
