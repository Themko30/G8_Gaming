<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Aggiungi Prodotto"/>
        <jsp:param name="styles" value="prenotazione"/>
    </jsp:include>
</head>
<body>
<div class="center">
    <h1>Aggiungi Prodotto</h1>
    <form method="post" onsubmit="return validate()" action="${context}/admin/Products/AddProduct" accept-charset="utf-8" enctype="multipart/form-data">
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
            <input type="file" name="copertina" id="copertina" required>
        </div>
        <div id="liveAlertPlaceholder"></div>
        <input type="submit" value="Aggiungi">
    </form>
</div>
<script>
    function validate() {
        let nome = $('#nome').val();
        let descrizione = $('#descrizione').val();
        let quantita = $('#quantita').val();
        let prezzo = $('#prezzo').val();
        let scontoAttivo = $('#scontoAttivo').val();
        let message = "<ul>";
        let valid = true;

        if(nome.length < 3 || nome.length > 100) {
            message += "<li>Il nome deve avere una lunghezza compresa tra i 3 e i 100 caratteri.</li>";
            valid = false;
        }
        if(descrizione.length < 10 || descrizione.length > 2048) {
            message += "<li>La descrizione deve avere una lunghezza compresa tra i 10 e i 2048 caratteri.</li>";
            valid = false;
        }
        if(Number(quantita) < 1) {
            message += "<li>La quantità non può essere minore di 1.</li>";
            valid = false;
        }
        if(Number(prezzo) < 0.01) {
            message += "<li>Il prezzo non può essere inferiore a 0,01.</li>";
            valid = false;
        }
        if(Number(scontoAttivo) < 0 || Number(scontoAttivo) > 0.99) {
            message += "<li>Lo sconto attivo deve essere compreso tra 0 e 0.99.</li>";
            valid = false;
        }

        if(!valid) {
            message += "</ul>";
            alertBox(message, "danger");
        }
        return valid;
    }
</script>
</body>
</html>
