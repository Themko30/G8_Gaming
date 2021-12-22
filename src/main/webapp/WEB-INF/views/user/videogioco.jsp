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
            <img src="${context}/images/demons_souls_ps5.jpg" class="w-75 img-fluid rounded">
        </div>
        <div class="col-md-7 mt-3">
            <p class="platf text-center"><a href="${context}/piattaforma/ps5">PlayStation 5</a></p>
            <h2>Demon's Souls</h2>
            <p class="categ"><a href="${context}/categoria/RPG">RPG</a></p>
            <span class="bi bi-star-fill"></span>
            <span class="bi bi-star-fill"></span>
            <span class="bi bi-star-fill"></span>
            <span class="bi bi-star-half"></span>
            <span class="bi bi-star"></span>
            <p class="prezzo">€39.99 <span class="sconto text-center">-50%</span></p>
            <p class="disponibilita">Disponibilità: In stock</p>
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
                            Da PlayStation Studios e Bluepoint Games arriva il remake di un classico di PlayStation, Demon's Souls. Interamente ricostruito da zero e magnificamente migliorato,
                            questo remake presenta gli orrori di una terra di fantasia oscura e schiacciata dalla nebbia, a una nuova generazione di giocatori. Coloro che hanno già affrontato
                            le sue sfide e le sue avversità potranno nuovamente misurarsi contro l'oscurità con una qualità visiva magnifica e godendo di prestazioni incredibili.<br>Nel corso
                            della sua missione per ottenere il potere, Re Allant, il dodicesimo re di Boletaria, ha incanalato le arti dell'anima, risvegliando un demone dall'alba del tempo,
                            l'Antico. Con l'evocazione dell'Antico, una nebbia incolore si diffuse sulla terra, liberando creature mostruose affamate di anime degli umani. Chi veniva privato
                            dell'anima usciva di senno, e non desiderava altro che attaccare le persone sane che rimanevano.<br>Oggi, Boletaria è tagliata fuori dal resto del mondo e i cavalieri
                            che osano penetrare la fitta nebbia per liberare la terra dalla sua piaga, non fanno mai più ritorno. Nei panni di un guerriero solitario che ha fronteggiato la nebbia
                            funesta, devi affrontare le sfide più dure per ottenere il titolo di "Uccisore di demoni" e rispedire l'Antico nel suo torpore.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../partials/header.jsp"%>
</body>
</html>
