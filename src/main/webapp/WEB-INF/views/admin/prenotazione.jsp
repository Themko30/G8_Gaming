<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Prenotazione"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container d-flex justify-content-center align-items-center my-5">
    <div class="col-lg-8">
        <div class="card mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">ID:</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${prenotazione.numeroPrenotazione}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Email:</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${prenotazione.emailRichiedente}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Nome gioco:</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${prenotazione.nomeProdotto}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Categoria:</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${prenotazione.categoria}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Descrizione:</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0">${prenotazione.descrizione}</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Copertina</p>
                    </div>
                    <div class="col-sm-9">
                        <p class="text-muted mb-0"><a href="${context}/images/${prenotazione.copertina}">${prenotazione.copertina}</a></p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <p class="mb-0">Accettata</p>
                    </div>
                    <div class="col-sm-9">
                        <c:choose>
                            <c:when test="${prenotazione.accettata == 1}">
                                <p class="text-muted mb-0">Sì</p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-muted mb-0">No</p>
                                <form method="post" action="${context}/admin/Booking/ManageBooking">
                                    <input type="hidden" value="${prenotazione.numeroPrenotazione}" name="numeroPrenotazione" required>
                                    <button type="submit" class="btn">Accetta</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
