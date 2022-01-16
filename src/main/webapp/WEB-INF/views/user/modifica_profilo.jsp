<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Modifica profilo"/>
        <jsp:param name="styles" value="registration"/>
        <jsp:param name="scripts" value="validators"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Modifica Profilo</h1>
    <form method="post" onsubmit="return validateRegistration()" action="${context}/account/update">
        <input type="hidden" id="username" name="username" value="${utente.username}" required>
        <div class="txt_field">
            <input type="text" id="nome" name="nome" value="${utente.nome}" required>
            <span></span>
            <label for="nome">Nome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="cognome" name="cognome" value="${utente.cognome}" required>
            <span></span>
            <label for="cognome">Cognome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="email" name="email" value="${utente.email}" required>
            <span></span>
            <label for="email">Email</label>
        </div>
        <div class="txt_field">
            <input type="password" id="oldPassword" name="oldPassword" required>
            <span></span>
            <label for="oldPassword">Vecchia Password</label>
        </div>
        <div class="txt_field">
            <input type="password" id="password" name="password" required>
            <span></span>
            <label for="password">Nuova Password</label>
        </div>
        <div class="txt_field">
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <span></span>
            <label for="confirmPassword">Conferma nuova password</label>
        </div>
        <div class="txt_field">
            <input type="date" id="data" name="data" required>
            <span></span>
            <label for="data">Data di nascita</label>
        </div>
        <select class="form-select" name="sesso" id="sesso" required>
            <option value="Maschio" selected>Maschio</option>
            <option value="Femmina">Femmina</option>
            <option value="Altro">Altro</option>
        </select>
        <div class="txt_field">
            <input type="text" id="indirizzo" name="indirizzo" value="${utente.indirizzo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Es. Via Roma, 15" required>
            <span></span>
            <label for="username">Indirizzo</label>
        </div>
        <div class="txt_field">
            <input type="text" id="cap" name="cap" minlength="5" maxlength="5" value="${utente.cap}" required>
            <span></span>
            <label for="username">CAP</label>
        </div>
        <div class="txt_field">
            <input type="text" id="paese" name="paese" value="${utente.paese}" required>
            <span></span>
            <label for="username">Paese</label>
        </div>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" id="register" value="Modifica">
    </form>
</div>
</body>
</html>
