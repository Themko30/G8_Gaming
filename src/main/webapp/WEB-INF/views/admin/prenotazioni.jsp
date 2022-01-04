<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Prenotazioni"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container-xxl my-5">
    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Email</th>
            <th scope="col">Gioco</th>
            <th scope="col">Copertina</th>
            <th scope="col">Accettata</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prenotazioni}" var="prenotazione">
            <tr>
                <th scope="row"><a href="${context}/admin/Booking/ManageBooking?numeroPrenotazione=${prenotazione.numeroPrenotazione}">${prenotazione.numeroPrenotazione}</a></th>
                <td>${prenotazione.emailRichiedente}</td>
                <td>${prenotazione.nome}</td>
                <td><a href="${context}/images/${prenotazione.copertina}">${prenotazione.copertina}</a></td>
                <c:choose>
                    <c:when test="${prenotazione.accettata==1}">
                        <td>Sì</td>
                    </c:when>
                    <c:otherwise>
                        <td>No</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
