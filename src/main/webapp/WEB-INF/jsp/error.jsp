<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Refresh" content="3;url=index">
<link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.css"/>
<head>
    <title></title>
</head>
<body>
<div style="text-align: center">
    <h1>Some error has occurred. Please try again later...</h1>
    <c:out value="Error code: ${requestScope.errorCode}"/>
</div>
</body>
</html>
