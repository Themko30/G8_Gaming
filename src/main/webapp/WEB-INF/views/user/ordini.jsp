<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Ordini"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container-xxl my-5">
    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Totale</th>
            <th scope="col">Data</th>
            <th scope="col">Indirizzo</th>
            <th scope="col">Stato</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ordini}" var="ordine">
            <tr>
                <th scope="row"><a href="${context}/account/orderView?codice=${ordine.numero}">${ordine.numero}</a></th>
                <td>${ordine.totale}€</td>
                <td>${ordine.data}</td>
                <td>${ordine.indirizzoSpedizione}</td>
                <td>${ordine.stato}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
