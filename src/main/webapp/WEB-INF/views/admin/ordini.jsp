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
<%@include file="../partials/admin_header.jsp"%>
<div class="container-xxl my-5">
    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Utente</th>
            <th scope="col">Totale</th>
            <th scope="col">Data</th>
            <th scope="col">Stato</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ordini}" var="ordine">
            <tr>
                <th scope="row"><a href="${context}/admin/Orders/Show?numero=${ordine.numero}">${ordine.numero}</a></th>
                <td><a href="${context}/admin/Users/Show?username=${ordine.utente.username}">${ordine.utente.username}</a></td>
                <td>${ordine.totale}€</td>
                <td>${ordine.data}</td>
                <td>${ordine.stato}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
