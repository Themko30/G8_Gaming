<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Registrazione"/>
        <jsp:param name="styles" value="registration"/>
        <jsp:param name="scripts" value="validators"/>
    </jsp:include>
</head>
<body>
<c:choose>
    <c:when test="${not empty pw}">
        <c:set var="us" scope="session" value="${us}"/>
        <c:set var="pw" scope="session" value="${pw}"/>
        <c:set var="pa" scope="session" value="${pa}"/>
        <c:set var="no" scope="session" value="${no}"/>
        <c:set var="co" scope="session" value="${co}"/>
        <c:set var="in" scope="session" value="${in}"/>
        <c:set var="ca" scope="session" value="${ca}"/>
        <c:set var="em" scope="session" value="${em}"/>
    </c:when>
    <c:otherwise>
        <c:set var="us" scope="session" value=""/>
        <c:set var="pw" scope="session" value=""/>
        <c:set var="pa" scope="session" value=""/>
        <c:set var="no" scope="session" value=""/>
        <c:set var="co" scope="session" value=""/>
        <c:set var="in" scope="session" value=""/>
        <c:set var="em" scope="session" value=""/>
        <c:set var="ca" scope="session" value=""/>
    </c:otherwise>
</c:choose>
<div class="center">
    <h1>Registrazione</h1>
    <form method="post" onsubmit="return validateRegistration()" action="${context}/registrazione/save">
        <div id="alertPlaceholder"></div>
        <c:if test="${errore == 1}">
            <script>
                $(document).ready(function() {
                    alertBox("Username o email già in uso!", "danger", "#alertPlaceholder");
                });
            </script>
        </c:if>
        <div class="txt_field">
            <input type="text" id="nome" name="nome" value="${no}" required>
            <span></span>
            <label for="nome">Nome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="cognome" name="cognome" value="${co}" required>
            <span></span>
            <label for="cognome">Cognome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="email" name="email" value="${em}" required>
            <span></span>
            <label for="email">Email</label>
        </div>
        <div class="txt_field">
            <input type="text" id="username" name="username" value="${us}" required>
            <span></span>
            <label for="username">Username</label>
        </div>
        <div class="txt_field">
            <input type="password" id="password" name="password" value="${pw}" required>
            <span></span>
            <label for="password">Password</label>
        </div>
        <div class="txt_field">
            <input type="password" id="confirmPassword" name="confirmPassword" value="${pw}" required>
            <span></span>
            <label for="confirmPassword">Conferma password</label>
        </div>
        <div class="txt_field">
            <input type="date" id="data" name="data" required>
            <span></span>
            <label for="data">Data di nascita</label>
        </div>
        <select class="form-select" name="sesso" id="sesso" required>
            <option selected disabled>Sesso</option>
            <option value="Maschio">Maschio</option>
            <option value="Femmina">Femmina</option>
            <option value="Altro">Altro</option>
        </select>
        <div class="txt_field">
            <input type="text" id="indirizzo" name="indirizzo" value="${in}" required>
            <span></span>
            <label for="username">Indirizzo</label>
        </div>
        <div class="txt_field">
            <input type="text" id="cap" name="cap" minlength="5" maxlength="5" value="${ca}" required>
            <span></span>
            <label for="username">CAP</label>
        </div>
        <div class="txt_field">
            <input type="text" id="paese" name="paese" value="${pa}" required>
            <span></span>
            <label for="username">Paese</label>
        </div>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" id="register" value="Registrati">
        <div class="signup_link">
            Hai già un account? <a href="${context}/account/login">Accedi</a>
        </div>
    </form>
</div>
</body>
</html>
