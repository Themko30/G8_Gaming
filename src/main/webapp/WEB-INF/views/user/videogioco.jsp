<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Videogioco"/>
        <jsp:param name="styles" value="videogioco"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
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
                    <c:set var="prezzo" scope="session" value="${prodotto.prezzo - (prodotto.prezzo*prodotto.scontoAttivo)}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="prezzo" scope="session" value="${prodotto.prezzo}"/>
                </c:otherwise>
            </c:choose>
            <p class="prezzo"><span id="actualPrice">${prezzo}</span>
                <c:if test="${prodotto.scontoAttivo > 0}">
                    <span style="text-decoration: line-through; font-size: 18px; margin: 0 4px;">€${prodotto.prezzo}</span> <span class="sconto text-center">-${prodotto.scontoAttivo*100}%</span>
                </c:if> </p>

            <c:choose>
                <c:when test="${prodotto.quantita > 0}">
                    <c:set var="disponibilita" scope="session" value="${prodotto.quantita}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="disponibilita" scope="session" value="Esaurito"/>
                </c:otherwise>
            </c:choose>
            <p class="disponibilita">Disponibilità: ${disponibilita}</p>
            <label>Quantità:</label>
            <input id="quantita" type="number" value="1" min="1" step="1" required>
            <button class="btn carrello">Aggiungi al carrello</button>
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
</div>
<script>
    $(document).ready(function() {
        let span = $('#actualPrice');
        const prezzo = Number(span.text()); // Seleziona il prezzo e lo converte in numero
        span.text("€" + prezzo.toFixed(2)); // Imposta due cifre decimali
    });
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
