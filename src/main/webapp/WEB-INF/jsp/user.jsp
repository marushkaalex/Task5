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
    ${shownUser.username}
    <m:show_gallery paintings="${gallery}"/>
    <m:show_gallery paintings="${likes}"/>
</div>
</body>
</html>
