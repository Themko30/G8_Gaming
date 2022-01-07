<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Modifica Prodotto"/>
        <jsp:param name="styles" value="prenotazione"/>
        <jsp:param name="scripts" value="validators"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Modifica Prodotto</h1>
    <form method="post" onsubmit="return validateAddProduct()" action="${context}/admin/Products/ModifyProduct" accept-charset="utf-8" enctype="multipart/form-data">
        <input type="hidden" name="codice" value="${prodotto.codice}" required>
        <div class="txt_field">
            <input type="text" id="nome" name="nome" value="${prodotto.nome}" required>
            <span></span>
            <label for="nome">Nome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="descrizione" name="descrizione" value="${prodotto.descrizione}" required>
            <span></span>
            <label for="nome">Descrizione</label>
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
        <select class="form-select" name="piattaforma" id="piattaforma" required>
            <option value="PlayStation 5" selected>PlayStation 5</option>
            <option value="Xbox Series X|S">Xbox Series X|S</option>
            <option value="Nintendo Switch">Nintendo Switch</option>
            <option value="PC">PC</option>
            <option value="PlayStation 4">PlayStation 4</option>
            <option value="Xbox One">Xbox One</option>
        </select>
        <div class="txt_field">
            <input type="number" step="1" id="quantita" name="quantita" value="${prodotto.quantita}" required>
            <span></span>
            <label for="quantita">Quantità in magazzino</label>
        </div>
        <div class="txt_field">
            <input type="number" step="0.01" id="prezzo" name="prezzo" value="${prodotto.prezzo}" required>
            <span></span>
            <label for="nome">Prezzo</label>
        </div>
        <div class="txt_field">
            <input type="number" step="0.01" id="scontoAttivo" name="scontoAttivo" value="${prodotto.scontoAttivo}" required>
            <span></span>
            <label for="nome">Sconto attivo</label>
        </div>
        <div>
            <label for="copertina">Immagine</label>
            <input type="file" accept=".jpg,.png" name="copertina" id="copertina" required>
        </div>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" value="Modifica">
    </form>
</div>
</body>
</html>
