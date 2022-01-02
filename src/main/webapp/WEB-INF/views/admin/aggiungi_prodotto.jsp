<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Aggiungi Prodotto"/>
        <jsp:param name="styles" value="prenotazione"/>
        <jsp:param name="scripts" value="validators"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Aggiungi Prodotto</h1>
    <form method="post" onsubmit="return validateAddProduct()" action="${context}/admin/Products/AddProduct" accept-charset="utf-8" enctype="multipart/form-data">
        <div class="txt_field">
            <input type="text" id="nome" name="nome" required>
            <span></span>
            <label for="nome">Nome</label>
        </div>
        <div class="txt_field">
            <input type="text" id="descrizione" name="descrizione" required>
            <span></span>
            <label for="nome">Descrizione</label>
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
        <select class="form-select" name="piattaforma" id="piattaforma" required>
            <option selected disabled>Piattaforma</option>
            <option value="ps5">PlayStation 5</option>
            <option value="seriesx">Xbox Series X|S</option>
            <option value="switch">Nintendo Switch</option>
            <option value="pc">PC</option>
            <option value="ps4">PlayStation 4</option>
            <option value="xboxone">Xbox One</option>
        </select>
        <div class="txt_field">
            <input type="number" step="1" id="quantita" name="quantita" required>
            <span></span>
            <label for="quantita">Quantità in magazzino</label>
        </div>
        <div class="txt_field">
            <input type="number" step="0.01" id="prezzo" name="prezzo" required>
            <span></span>
            <label for="nome">Prezzo</label>
        </div>
        <div class="txt_field">
            <input type="number" step="0.01" id="scontoAttivo" name="scontoAttivo" required>
            <span></span>
            <label for="nome">Sconto attivo</label>
        </div>
        <div>
            <label for="copertina">Immagine</label>
            <input type="file" accept=".jpg,.png" name="copertina" id="copertina" required>
        </div>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" value="Aggiungi">
    </form>
</div>
</body>
</html>
