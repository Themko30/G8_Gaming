<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Registrazione"/>
        <jsp:param name="styles" value="registration"/>
        <jsp:param name="scripts" value="registration"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Registrazione</h1>
    <form method="post" onsubmit="return validate()" action="#">
        <div class="txt_field">
            <input type="text" id="name" name="name" required>
            <span></span>
            <label for="name">Nome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="surname" name="surname" required>
            <span></span>
            <label for="surname">Cognome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="email" name="email" required>
            <span></span>
            <label for="email">Email</label>
        </div>
        <div class="txt_field">
            <input type="text" id="username" name="username" required>
            <span></span>
            <label for="username">Username</label>
        </div>
        <div class="txt_field">
            <input type="password" id="password" name="password" required>
            <span></span>
            <label for="password">Password</label>
        </div>
        <div class="txt_field">
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <span></span>
            <label for="confirmPassword">Conferma password</label>
        </div>
        <div class="txt_field">
            <input type="date" id="dataDiNascita" name="dataDiNascita" required>
            <span></span>
            <label for="dataDiNascita">Data di nascita</label>
        </div>
        <select class="form-select" name="sesso" id="sesso" required>
            <option selected disabled value="maschio">Sesso</option>
            <option value="maschio">Maschio</option>
            <option value="femmina">Femmina</option>
            <option value="altro">Altro</option>
        </select>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" id="register" value="Registrati">
        <div class="signup_link">
            Hai già un account? <a href="http://localhost:8080/G8_Gaming_war_exploded/account/login">Accedi</a>
        </div>
    </form>
</div>
</body>
</html>
