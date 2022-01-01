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
        <tr>
            <th scope="row"><a href="#">1</a></th>
            <td><a href="#">giulio01</a></td>
            <td>39.99€</td>
            <td>30/12/2021</td>
            <td>In lavorazione</td>
        </tr>
        <tr>
            <th scope="row"><a href="#">2</a></th>
            <td><a href="#">giulio01</a></td>
            <td>39.99€</td>
            <td>30/12/2021</td>
            <td>In lavorazione</td>
        </tr>
        <tr>
            <th scope="row"><a href="#">3</a></th>
            <td><a href="#">giulio01</a></td>
            <td>39.99€</td>
            <td>30/12/2021</td>
            <td>In lavorazione</td>
        </tr>
        <tr>
            <th scope="row"><a href="#">4</a></th>
            <td><a href="#">giulio01</a></td>
            <td>39.99€</td>
            <td>30/12/2021</td>
            <td>In lavorazione</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
