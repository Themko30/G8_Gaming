<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${not empty utente}">
        <c:set var="email" value="${utente.email}"/>
    </c:when>
    <c:otherwise>
        <c:set var="emai" value=""/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Prenotazione"/>
        <jsp:param name="styles" value="prenotazione"/>
        <jsp:param name="scripts" value="validators"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Prenotazione</h1>
    <form method="post" onsubmit="return validateBooking()" action="${context}/prenotazione/save" enctype="multipart/form-data">
        <div class="txt_field">
            <input type="text" id="email" name="email" value="${email}" required>
            <span></span>
            <label for="email">Email</label>
        </div>
        <div class="txt_field">
            <input type="text" id="nomeProdotto" name="nomeProdotto" required>
            <span></span>
            <label for="nomeProdotto">Nome gioco</label>
        </div>
        <div class="txt_field">
            <input type="text" id="descrizione" name="descrizione" required>
            <span></span>
            <label for="descrizione">Descrizione</label>
        </div>
        <select class="form-select" name="categoria" id="categoria" required>
            <option value="Action/Adventure" selected>Action/Adventure</option>
            <option value="Platform">Platform</option>
            <option value="Guida">Guida</option>
            <option value="Picchiaduro">Picchiaduro</option>
            <option value="RPG">RPG</option>
            <option value="Sparatutto">Sparatutto</option>
            <option value="Simulazione">Simulazione</option>
            <option value="Sport">Sport</option>
            <option value="Strategia">Strategia</option>
        </select>
        <div>
            <label for="copertina">Immagine</label>
            <input type="file" accept=".jpg,.png" name="copertina" id="copertina" required>
        </div>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" id="register" value="Prenota">
    </form>
</div>
</body>
</html>
