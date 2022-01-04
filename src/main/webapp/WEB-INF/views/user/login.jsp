<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${not empty pw}">
        <c:set var="us" value="${us}"/>
        <c:set var="pw" value="${pw}"/>
    </c:when>
    <c:otherwise>
        <c:set var="us" value=""/>
        <c:set var="pw" value=""/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login"/>
        <jsp:param name="styles" value="login"/>
        <jsp:param name="scripts" value="validators"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Login</h1>
    <form method="post" onsubmit="return validateLogin()" action="${context}/account/login">
        <div class="txt_field">
            <input type="text" name="username" id="username" value="${us}" required>
            <span></span>
            <label>Username</label>
        </div>
        <div class="txt_field">
            <input type="password" name="password" id="password" value="${pw}" required>
            <span></span>
            <label>Password</label>
        </div>
        <c:if test="${errate == 1}">
            <script>
                $(document).ready(function() {
                    alertBox("Credenziali errate!", "danger");
                });
            </script>
        </c:if>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" value="Login">
        <div class="signup_link">
            Non hai un account? <a href="${context}/registrazione/">Registrati</a>
        </div>
    </form>
</div>
</body>
</html>
