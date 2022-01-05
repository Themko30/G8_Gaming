<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="${piattaforma}"/>
    <jsp:param name="styles" value="home"/>
  </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container">
  <p class="h1 text-center my-3">Videogiochi per ${piattaforma}</p>

  <div class="container d-flex justify-content-center align-items-center">
    <div class="row my-2">
      <c:forEach items="${prodotti}" var="prodotto">
        <div class="col-sm my-2 mx-3">
          <div class="card text-center" id="${prodotto.codice}" style="width: 12rem;">
            <img src="${context}/images/${prodotto.copertina}" class="card-img-top p-2" alt="${prodotto.copertina}">
            <div class="card-body">
              <h5 class="card-title">${prodotto.nome}<br>
                <span class="price">${prodotto.prezzo - (prodotto.prezzo*prodotto.scontoAttivo)}</span></h5>
              <h6 class="card-subtitle text-muted">${prodotto.categoria}</h6>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>

  <div class="container text-center my-5">
    <h2>Prenotazione</h2>
    <p>Non trovi un videogioco? <a href="${context}/prenotazione/">Effettua una prenotazione</a></p>
  </div>
</div>
<script>
  $(document).ready(function() {
    $('.card').click(function(){
      window.location.href = '${context}/Prodotto/Visualizza?prodotto=' + $(this).attr("id");
    });

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
