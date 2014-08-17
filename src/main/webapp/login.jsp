<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">

    <title>Login Form - CodePen</title>

    <link rel="stylesheet" href="static/style/css/login.css" media="screen" type="text/css"/>

</head>

<body>

<span href="#" class="button" id="toggle-login">Log in</span>

<div id="login">
    <div id="triangle"></div>
    <h1></h1>

    <form action="${pageContext.request.contextPath}/do/logincheck" method="post">
    <input type="text" placeholder="Username" name="username"/>
        <input type="password" placeholder="Password" name="password"/>
        <input type="submit" value="Submit"/>
    </form>
</div>

</body>

</html>
