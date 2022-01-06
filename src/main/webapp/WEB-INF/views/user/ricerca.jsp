<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="${ricerca}"/>
        <jsp:param name="styles" value="home"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container">
    <p class="h1 text-center my-3">Risultati di ricerca per "${ricerca}"</p>

    <div class="container d-flex justify-content-center align-items-center">
        <div class="row my-2">
            <c:forEach items="${prodotti}" var="prodotto">
                <div class="col-sm my-2 mx-3">
                    <div class="card text-center" id="${prodotto.codice}" style="width: 12rem;">
                        <img src="${context}/images/${prodotto.copertina}" class="card-img-top p-2" alt="${prodotto.copertina}">
                        <div class="card-body">
                            <h5 class="card-title">${prodotto.nome}<br>
                                <span class="price">€${prodotto.prezzoScontato}</span></h5>
                            <h6 class="card-subtitle text-muted">${prodotto.categoria}<br>${prodotto.piattaforma}</h6>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="container text-center my-5">
        <h2>Prenotazione</h2>
        <p>Non trovi un videogioco? <a href="${context}/prenotazione">Effettua una prenotazione</a></p>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('.card').click(function(){
            window.location.href = '${context}/Prodotto/Visualizza?prodotto=' + $(this).attr("id");
        });
    });
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
