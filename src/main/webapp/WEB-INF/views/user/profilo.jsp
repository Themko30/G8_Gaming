<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Il mio profilo"/>
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
                        <p class="mb-0">Nome</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.nome} ${utente.cognome}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Email</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.email}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Username</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.username}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Sesso</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.sesso}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Data di nascita</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.dataDiNascita}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Indirizzo</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.indirizzo}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">CAP</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.cap}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Paese</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${utente.paese}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container d-flex justify-content-center align-items-center my-5">
    <a href="${context}/account/update"><button class="btn mx-2">Modifica Profilo</button></a>
    <a href="${context}/account/ordersPage"><button class="btn mx-2">Visualizza ordini</button></a>
    <button class="btn btn-danger mx-2">Elimina profilo</button>
</div>

<%@include file="../partials/footer.jsp"%>
</body>
</html>
