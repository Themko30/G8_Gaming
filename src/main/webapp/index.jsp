<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Home"/>
        <jsp:param name="styles" value="home"/>
    </jsp:include>
</head>
<body>
<%@include file="WEB-INF/views/partials/header.jsp"%>
<div class="container">

    <p class="h1 text-center my-3">Videogiochi in evidenza</p>
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row my-2">
            <c:forEach items="${home}" var="prodotto">
                <div class="col-sm my-2 mx-3">
                    <div class="card text-center prodotto" id="${prodotto.codice}" style="width: 12rem;">
                        <img src="${context}/images/${prodotto.copertina}" class="card-img-top p-2" alt="">
                        <div class="card-body">
                            <h5 class="card-title">${prodotto.nome}<br>
                                <span class="price">${prodotto.prezzo - (prodotto.prezzo*prodotto.scontoAttivo)}</span></h5>
                            <h6 class="card-subtitle text-muted">${prodotto.categoria}<br>${prodotto.piattaforma}</h6>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <p class="h1 text-center my-3">I più votati</p>
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row my-2">
            <c:forEach items="${mostVoted}" var="prodotto">
                <div class="col-sm my-2 mx-3">
                    <div class="card text-center prodotto" id="${prodotto.codice}" style="width: 12rem;">
                        <img src="${context}/images/${prodotto.copertina}" class="card-img-top p-2" alt="">
                        <div class="card-body">
                            <h5 class="card-title">${prodotto.nome}<br>
                                <span class="price">${prodotto.prezzo - (prodotto.prezzo*prodotto.scontoAttivo)}</span></h5>
                            <h6 class="card-subtitle text-muted">${prodotto.categoria}<br>${prodotto.piattaforma}</h6>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <p class="h1 text-center my-3">Categorie più popolari</p>
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row my-2">
            <div class="col-sm my-2 mx-3">
                <div class="card text-center categoria" id="Action%2FAdventure" style="width: 12rem;">
                    <img src="${context}/images/action.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">Action/Adventure<br></h5>
                    </div>
                </div>
            </div>

            <div class="col-sm my-2 mx-3">
                <div class="card text-center categoria" id="RPG" style="width: 12rem;">
                    <img src="${context}/images/rpg.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">RPG<br></h5>
                    </div>
                </div>
            </div>

            <div class="col-sm my-2 mx-3">
                <div class="card text-center categoria" id="Sparatutto" style="width: 12rem;">
                    <img src="${context}/images/fps.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">Sparatutto<br></h5>
                    </div>
                </div>
            </div>

            <div class="col-sm my-2 mx-3">
                <div class="card text-center categoria" id="Platform" style="width: 12rem;">
                    <img src="${context}/images/platform.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">Platform<br></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <p class="h1 text-center my-3">Piattaforme più diffuse</p>
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row my-2">
            <div class="col-sm my-2 mx-3">
                <div class="card text-center piattaforma" id="PlayStation+5" style="width: 12rem;">
                    <img src="${context}/images/ps5.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">PlayStation 5<br></h5>
                    </div>
                </div>
            </div>

            <div class="col-sm my-2 mx-3">
                <div class="card text-center piattaforma" id="Xbox+Series+X%7CS" style="width: 12rem;">
                    <img src="${context}/images/seriesx.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">Xbox Series X|S<br></h5>
                    </div>
                </div>
            </div>

            <div class="col-sm my-2 mx-3">
                <div class="card text-center piattaforma" id="Nintendo+Switch" style="width: 12rem;">
                    <img src="${context}/images/switch.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">Nintendo Switch<br></h5>
                    </div>
                </div>
            </div>

            <div class="col-sm my-2 mx-3">
                <div class="card text-center piattaforma" id="PC" style="width: 12rem;">
                    <img src="${context}/images/pc.jpg" class="card-img-top p-2" alt="">
                    <div class="card-body">
                        <h5 class="card-title">PC<br></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container text-center my-5">
        <h2>Prenotazione</h2>
        <p>Non trovi un videogioco? <a href="${context}/prenotazione/">Effettua una prenotazione</a></p>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('.prodotto').click(function(){
            window.location.href = '${context}/Prodotto/Visualizza?prodotto=' + $(this).attr("id");
        });
        $('.piattaforma').click(function(){
            window.location.href = '${context}/Prodotto/Piattaforma?piattaforma=' + $(this).attr("id");
        });
        $('.categoria').click(function(){
            window.location.href = '${context}/Prodotto/Categoria?categoria=' + $(this).attr("id");
        });

        let span = $('.price');
        for(let i = 0; i < span.length; i++) {
            const prezzo = Number(span[i].textContent); // Seleziona il prezzo e lo converte in numero
            span[i].innerText = "€" + prezzo.toFixed(2); // Imposta due cifre decimali
        }
    });
</script>
<%@include file="WEB-INF/views/partials/footer.jsp"%>
</body>
</html>
