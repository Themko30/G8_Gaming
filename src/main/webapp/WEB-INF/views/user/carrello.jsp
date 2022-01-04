<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Carrello"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container-xxl my-5">
    <div class="text-center my-2">
        <span id="totale"></span>
        <a href="#"><button type="button" class="btn mx-2">Procedi all'ordine</button></a>
    </div>

    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Prodotto</th>
            <th scope="col">Quantità</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Rimuovi</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carrello.prodotti.keySet()}" var="prodotto">
            <tr>
                <c:set var="quantita" value="${carrello.prodotti.get(prodotto)}"/>

                <th scope="row"><a href="${context}/Prodotto/Visualizza?prodotto=${prodotto.codice}">${prodotto.nome}</a></th>
                <td>${quantita}</td>
                <td><span class="total">${(prodotto.prezzo - prodotto.prezzo * prodotto.scontoAttivo) * quantita}</span></td>
                <td><form method="post" action="${context}/cart/Rimuovi">
                    <input type="hidden" name="prodotto" value="${prodotto.codice}" required>
                    <button type="submit" class="btn btn-secondary">Rimuovi</button>
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form method="post" action="">
        <input type="hidden" name="" value="">
        <button type="button" class="btn btn-danger">Svuota Carrello</button>
    </form>
</div>
<script>
    $(document).ready(function() {
        let span = $('.total');
        let totale = 0;
        for(let i = 0; i < span.length; i++) {
            const prezzo = Number(span[i].textContent); // Seleziona il prezzo e lo converte in numero
            totale += prezzo;
            span[i].innerText = "€" + prezzo.toFixed(2); // Imposta due cifre decimali
        }
        $('#totale').text("Totale: €" + totale.toFixed(2));
    });
</script>
</body>
</html>
