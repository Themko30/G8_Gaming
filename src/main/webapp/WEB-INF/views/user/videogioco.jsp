<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="${prodotto.nome}"/>
        <jsp:param name="styles" value="videogioco"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>

<c:choose>
    <c:when test="${not empty utente}">
        <c:set var="formAction" value="method=\"post\" onsubmit=\"return checkQuantity()\" action=\"${context}/cart/Add\""/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="method=\"get\" action=\"${context}/account/login\""/>
    </c:otherwise>
</c:choose>
<div class="container my-4">
    <div class="row">
        <div class="col-md-5">
            <img src="${context}/images/${prodotto.copertina}" class="w-75 img-fluid rounded">
        </div>
        <div class="col-md-7 mt-3">
            <p class="platf text-center"><a href="${context}/Prodotto/Piattaforma?piattaforma=${prodotto.piattaforma}">${prodotto.piattaforma}</a></p>
            <h2>${prodotto.nome}</h2>
            <p class="categ"><a href="${context}/Prodotto/Categoria?categoria=${prodotto.categoria}">${prodotto.categoria}</a></p>

            <c:forEach begin="1" end="${prodotto.mediaArrotondata}">
                <span class="bi bi-star-fill"></span>
            </c:forEach>
            <c:forEach begin="1" end="${5 - prodotto.mediaArrotondata}">
                <span class="bi bi-star"></span>
            </c:forEach>

            <!-- Se c'è lo sconto, calcolo il prezzo e aggiungo il vecchio prezzo e lo sconto applicato -->
            <c:choose>
                <c:when test="${prodotto.scontoAttivo > 0}">
                    <c:set var="prezzo" value="${prodotto.prezzo - (prodotto.prezzo*prodotto.scontoAttivo)}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="prezzo" value="${prodotto.prezzo}"/>
                </c:otherwise>
            </c:choose>
            <p class="prezzo"><span id="actualPrice">${prezzo}</span>
                <c:if test="${prodotto.scontoAttivo > 0}">
                    <span style="text-decoration: line-through; font-size: 18px; margin: 0 4px;">€${prodotto.prezzo}</span> <span class="sconto text-center">-${prodotto.scontoAttivo*100}%</span>
                </c:if> </p>

            <p class="disponibilita">Disponibilità: ${prodotto.quantita}</p>
            <form ${formAction}>
                <c:if test="${not empty utente}">
                    <input type="hidden" name="prodotto" value="${prodotto.codice}" required>
                </c:if>
                <label for="quantita">Quantità:</label>
                <input id="quantita" name="quantita" type="number" value="1" min="1" step="1" required>
                <button type="submit" class="btn carrello">Aggiungi al carrello</button>
            </form>
            <div class="accordion my-4" id="accordionDescrizione">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse" aria-expanded="false" aria-controls="collapse">
                            Descrizione
                        </button>
                    </h2>
                    <div id="collapse" class="accordion-collapse collapse" aria-labelledby="heading" data-bs-parent="#accordionDescrizione">
                        <div class="accordion-body">
                            ${prodotto.descrizione}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
        <div id="liveToast" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    Inserisci una quantità valida!
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</div>

<!-- TODO: prodotti consigliati -->
<script>
    $(document).ready(function() {
        let span = $('#actualPrice');
        const prezzo = Number(span.text()); // Seleziona il prezzo e lo converte in numero
        span.text("€" + prezzo.toFixed(2)); // Imposta due cifre decimali
    });

    function checkQuantity() {
        let disponibili = ${prodotto.quantita};
        let quantita = $('#quantita').val();
        if(quantita > disponibili) {
            $('#liveToast').toast("show");
            return false;
        }
        return true;
    }
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
