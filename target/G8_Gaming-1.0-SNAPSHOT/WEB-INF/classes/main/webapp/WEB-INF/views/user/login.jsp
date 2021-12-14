<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login Test</title>
        <link rel="stylesheet" href="${context}/css/login.css">
    </head>
    <body>
        <div class="center">
            <h1>Login</h1>
            <form method="post" action="">
                <div class="txt_field">
                    <input type="text" name="username" required>
                    <span></span>
                    <label>Username</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="password" required>
                    <span></span>
                    <label>Password</label>
                </div>
                <input type="submit" value="Login">
                <div class="signup_link">
                    Non hai un account? <a href="http://localhost:8080/CultureUniverse_war_exploded/registration.html">Registrati</a>
                </div>
            </form>
        </div>
    </body>
</html>
