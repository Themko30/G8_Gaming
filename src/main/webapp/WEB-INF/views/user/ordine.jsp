<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Ordine"/>
  </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container d-flex justify-content-center align-items-center my-5">
  <div class="col-lg-8">
    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">ID:</p>
          </div>
          <div class="col-sm-9">
            <p class="text-muted mb-0">${ordine.numero}</p>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">Totale:</p>
          </div>
          <div class="col-sm-9">
            <p class="text-muted mb-0">€${ordine.totale}</p>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">Indirizzo di spedizione:</p>
          </div>
          <div class="col-sm-9">
            <p class="text-muted mb-0">${ordine.indirizzoSpedizione}</p>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">Metodo di pagamento</p>
          </div>
          <div class="col-sm-9">
            <p class="text-muted mb-0">${ordine.metodoPagamento}</p>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">Data</p>
          </div>
          <div class="col-sm-9">
            <p class="text-muted mb-0">${ordine.data}</p>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">Stato</p>
          </div>
          <div class="col-sm-9">
            <p class="text-muted mb-0">${ordine.stato}</p>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-sm-3">
            <p class="mb-0">Prodotti:</p>
          </div>
          <div class="col-sm-9">
            <c:forEach items="${ordine.prodotti.keySet()}" var="prodotto">
              <p class="text-muted mb-0"><a href="${context}/Prodotto/Visualizza?prodotto=${prodotto.codice}">${prodotto.nome}</a>
                x ${ordine.prodotti.get(prodotto)} = <span class="price">${(prodotto.prezzo - prodotto.prezzo * prodotto.scontoAttivo) * ordine.prodotti.get(prodotto)}</span></p>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
  $(document).ready(function() {
    let span = $('.price');
    for(let i = 0; i < span.length; i++) {
      const prezzo = Number(span[i].textContent); // Seleziona il prezzo e lo converte in numero
      span[i].innerText = "€" + prezzo.toFixed(2); // Imposta due cifre decimali
    }
  });
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
