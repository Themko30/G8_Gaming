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
                x ${ordine.prodotti.get(prodotto)} = <span class="price">${(prodotto.prezzo-prodotto.prezzo*prodotto.scontoAttivo)*ordine.prodotti.get(prodotto)}</span></p>
              <c:if test="${prodotto.valutato != 1}">
                <button id="${prodotto.codice}" class="btn valuta mb-1">Valuta</button>
              </c:if>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="valutaModal" tabindex="-1" aria-labelledby="valutaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="valutaModalLabel">Valuta il gioco</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form method="post" action="${context}/account/updateValutazione" onsubmit="return checkValutazione()">
            <input type="hidden" name="codiceOrdine" id="codiceOrdine" value="${ordine.numero}" required>
            <input type="hidden" name="codiceProdotto" id="codiceProdotto" required>
            <label for="valutazione">La tua valutazione (da 1 a 5)</label>
            <input type="number" id="valutazione" name="valutazione" min="1" max="5" required>
            <button type="submit" class="btn">Invia</button>
          </form>
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

    $('.valuta').click(function(){
      $('#codiceProdotto').val($(this).attr("id"));
      $('#valutaModal').modal('show');
    });
  });

  function checkValutazione() {
    let v = Number($('#valutazione').val());
    return Number.isInteger(v) && v > 0 && v < 6;
  }
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
