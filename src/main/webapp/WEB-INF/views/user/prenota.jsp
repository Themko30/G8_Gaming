<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${not empty utente}">
        <c:set var="email" scope="session" value="${utente.email}"/>
    </c:when>
    <c:otherwise>
        <c:set var="emai" scope="session" value=""/>
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
            <input type="text" id="name" name="name" required>
            <span></span>
            <label for="name">Nome gioco</label>
        </div>
        <div class="txt_field">
            <input type="text" id="descrizione" name="descrizione" required>
            <span></span>
            <label for="name">Descrizione</label>
        </div>
        <select class="form-select" name="categoria" id="categoria" required>
            <option selected disabled>Categoria</option>
            <option value="action">Action/Adventure</option>
            <option value="casual">Casual</option>
            <option value="guida">Guida</option>
            <option value="picchiaduro">Picchiaduro</option>
            <option value="rpg">RPG</option>
            <option value="sparatutto">Sparatutto</option>
            <option value="simulazione">Simulazione</option>
            <option value="sport">Sport</option>
            <option value="strategia">Strategia</option>
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
