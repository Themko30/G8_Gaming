<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Registrati"/>
        <jsp:param name="styles" value="registration"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Registrazione</h1>
    <form method="post" action="">
        <div class="txt_field">
            <input type="text" id="name" name="name" required>
            <span></span>
            <label>Nome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="surname" name="surname" required>
            <span></span>
            <label>Cognome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="email" name="email" required>
            <span></span>
            <label>Email</label>
        </div>
        <div class="txt_field">
            <input type="text" id="username" name="username" required>
            <span></span>
            <label>Username</label>
        </div>
        <div class="txt_field">
            <input type="date" id="dataDiNascita" name="dataDiNascita" required>
            <span></span>
            <label>Data di nascita</label>
        </div>
        <div class="txt_field">
            <input type="password" id="password" name="password" required>
            <span></span>
            <label>Password</label>
        </div>
        <div class="txt_field">
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <span></span>
            <label>Conferma password</label>
        </div>
        <input type="submit" value="Registrati">
        <div class="signup_link">
            Hai già un account? <a href="http://localhost:8080/G8_Gaming_war_exploded/login">Accedi</a>
        </div>
    </form>
</div>
</body>
</html>
