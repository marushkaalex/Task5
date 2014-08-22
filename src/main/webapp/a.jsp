<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="i18n.messages">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <title>REGISTER PAGE</title>
        <link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.css">
    </head>
    <body>

    <div class="col-md-12">
        <form method="post">
            <input type="hidden" name="action" value="registration">

            <h2 class="form-signin-heading">REGISTRATION</h2>
            <input type="login" name="login" class="form-control" placeholder="Login" required autofocus>
                <%--<input type="email" name="email" class="form-control" placeholder="Email" required>--%>
            <input type="password" name="password" class="form-control" placeholder="Password" required>
                <%--<input type="repassword" name="repassword" class="form-control" placeholder="Confirm Password" required>       --%>
            <label>
                <select required name="role">
                    <option selected="selected" value="master">PhotoMaster</option>
                    <option value="client">Client</option>
                </select>
            </label>
                <%--<input type="checkbox" name="legal" value="legal" id="page" required> I'm 18 years old and agree with the Terms--%>
                <%--of Use and Privacy Policy.--%>
            <p></p>
            <button class="button" type="submit">Complete Registration!</button>
        </form>
    </div>

    </body>
    </html>
</fmt:bundle>