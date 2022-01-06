<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Prodotti"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container-xxl my-4">
    <a href="${context}/admin/Products/AddProduct"><button type="button" class="btn mb-4 mx-1">Nuovo Prodotto</button></a>
    <a href="${context}/admin/ShowHomePage"><button type="button" class="btn mb-4 mx-1">Mostra prodotti in evidenza</button></a>

    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nome</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Immagine</th>
            <th scope="col">Quantità</th>
            <th scope="col">In evidenza</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prodotti}" var="prodotto">
            <tr>
                <th scope="row">${prodotto.codice}</th>
                <td><a href="${context}/admin/Products/ModifyProduct?codice=${prodotto.codice}">${prodotto.nome}</a></td>
                <td>€${prodotto.prezzo}</td>
                <td><a href="${context}/images/${prodotto.copertina}">${prodotto.copertina}</a></td>
                <td>${prodotto.quantita}</td>
                <td><form method="post" action="${context}/admin/Products/AddProductHomePage">
                    <input type="hidden" value="${prodotto.codice}" name="codiceProdotto" required>
                    <button type="submit" class="btn">Aggiungi</button>
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
