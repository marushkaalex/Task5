<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/css/style.css">--%>
<link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.css"/>
<head>
    <title>Registration</title>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img class="img-rounded" src="static/image/logo.jpg"/></a>
        </div>
    </div>
</nav>

<div class="container">

    <h1>Registration Form</h1>

    <div class="col-md-8 col-centered">
        <form role="form" action="register" method="post">
            <div class="form-group">
                <label for="InputUsernasme">Username</label>
                <input type="text" class="form-control" id="InputUsernasme" placeholder="Username" name="username">
            </div>
            <div class="form-group">
                <label for="InputEmail">Email address</label>
                <input type="email" class="form-control" id="InputEmail" placeholder="Enter email" name="email">
            </div>
            <div class="form-group">
                <label for="InputPassword">Password</label>
                <input type="password" class="form-control" id="InputPassword" placeholder="Password" name="password">
            </div>
            <div class="form-group">
                <label for="DOB">Date of birth</label>
                <input id="DOB" class="form-control" type="date" name="dob">
                </label><br>
            </div>
            <div class="form-group">
                <label>
                    <select class="form-control" required name="role">
                        <option class="form-control" selected="selected" value="CLIENT">User</option>
                        <option class="form-control" value="ARTIST">Artist</option>
                    </select>
                </label><br>
            </div>
            <input type="submit" class="btn btn-default"/>
        </form>
        <c:set var="errorList" scope="page" value="${requestScope.register_errorList}"/>
        <c:out value="${errorList}"/>
        <c:if test="${errorList != null || errorList.size() == 0}">
            <div class="row">
                <h2>There are some errors</h2>
                <ul>
                <c:forEach var="i" begin="0" end="${errorList.size() - 1}">
            <li><c:out value="${errorList.get(i)}"/></li>
        </c:forEach>
    </ul>
            </div>
        </c:if>

    </div>
</div>
</body>
</html>
