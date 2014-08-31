<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="user" required="true" type="com.epam.am.entity.User" %>
<div id="sidebar-wrapper">
    <a href="<c:url value="/"/>">
        <img src="<c:url value="/static/image/logo.png"/>"/>
    </a>
    <ul class="sidebar-nav">
        <%--<li class="sidebar-brand">--%>
        <li>
            <a href="<c:url value="/artists"/>">Artists</a>
        </li>
        <c:if test="${not empty user}">
            <li>
                <a href="<c:url value="/acc"/>">${user.username}</a>
            </li>
            <c:if test="${user.role == 'ARTIST'}">
                <li>
                    <a href="<c:url value="/gallery"/>">Gallery</a>
                </li>
            </c:if>
            <li>
                <a href="<c:url value="/liked"/>">Liked</a>
            </li>
            <li>
                <a href="<c:url value="/logout"/>">Log out</a>
            </li>
        </c:if>
        <c:if test="${empty user}">
            <li>
                <a href="<c:url value="/login"/>">Log In</a>
            </li>
            <li>
                <a href="<c:url value="/register"/>">Register</a>
            </li>

        </c:if>
    </ul>
</div>