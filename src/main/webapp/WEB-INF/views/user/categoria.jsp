<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="${categoria}"/>
        <jsp:param name="styles" value="home"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container">
    <p class="h1 text-center my-3">Videogiochi categoria ${categoria}</p>

    <div class="row my-2">
        <c:forEach items="${prodotti}" var="prodotto">
            <div class="col-sm m-3">
                <div class="card text-center" id="${prodotto.codice}" style="width: 12rem;">
                    <img src="${context}/images/${prodotto.copertina}" class="card-img-top p-2" alt="${prodotto.copertina}">
                    <div class="card-body">
                        <h5 class="card-title">${prodotto.nome}<br>
                        <span id="price">${prodotto.prezzo - (prodotto.prezzo*prodotto.scontoAttivo)}</span></h5>
                        <h6 class="card-subtitle text-muted">${prodotto.piattaforma}</h6>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    $(document).ready(function() {
        let span = $('#price');
        const prezzo = Number(span.text()); // Seleziona il prezzo e lo converte in numero
        span.text("€" + prezzo.toFixed(2)); // Imposta due cifre decimali
    });
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
