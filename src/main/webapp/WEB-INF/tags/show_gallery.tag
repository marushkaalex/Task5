<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="paintings" required="true" type="com.epam.am.entity.Gallery" %>
<form action="<c:url value="/painting"/>" method="post">
    <c:forEach var="painting" items="${paintings.links}">
        <div class="col-md-4">
            <h3>${painting.key.name}</h3><br>
            <button name="painting" value="${painting.key}" type="submit">
                <img class="img-thumbnail" style="height: 170px; width: auto; max-width: 500px"
                     src="../<c:out value="${painting.value}"/>"/>
            </button>
        </div>
    </c:forEach>
</form>