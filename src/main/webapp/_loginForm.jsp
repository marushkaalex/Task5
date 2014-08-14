<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 12.08.2014
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="style/css/style.css">
<head>
    <title></title>
</head>
<body>
<div style="margin-left: auto;margin-right: auto; width: 80%"><h1 class="center">Login Page</h1>

    <form id="loginform" action="loginCheck.jsp" method="post"><h2 class="center">Login Details</h2><br/>Username:
        &nbsp;<input type="text" name="username">
        <br/><br>Password: &nbsp;<input type="password" name="password">
        <br/><br><br><input id="submit_button" type="submit" value="Submit">
    </form>
</div>

</body>
</html>

