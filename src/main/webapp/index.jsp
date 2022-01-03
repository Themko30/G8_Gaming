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

    <!-- TODO: dettagli dinamici delle cards -->
    <div class="row my-2">
        <div class="col-sm m-3">
            <div class="card text-center" id="1" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>

        <div class="col-sm m-3">
            <div class="card text-center" style="width: 12rem;">
                <img src="${context}/images/demons_souls_ps5.jpg" class="card-img-top p-2" alt="">
                <div class="card-body">
                    <h5 class="card-title">Demon's Souls</h5>
                    <h6 class="card-subtitle text-muted">RPG</h6>
                </div>
            </div>
        </div>
    </div>

    <div class="container text-center">
        <h2>Prenotazione</h2>
        <p>Non trovi un videogioco? <a href="${context}/prenotazione">Effettua una prenotazione</a></p>
    </div>
</div>
<%@include file="WEB-INF/views/partials/footer.jsp"%>
</body>
</html>
