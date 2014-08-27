<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach begin="0" end="${requestScope.gallery.size}" var="i">
    ${i};
</c:forEach>
</body>
</html>
