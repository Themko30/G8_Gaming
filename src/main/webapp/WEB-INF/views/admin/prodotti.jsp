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
    <button type="button" class="btn mb-4" id="aggiungi" style="background-color: var(--primary); color: var(--d3light);">Nuovo Prodotto</button>
    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nome</th>
            <th scope="col">Categoria</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Immagine</th>
            <th scope="col">Quantità</th>
        </tr>
        </thead>
        <tbody>
        <h1>${prodotti}</h1>
        <c:forEach items="${prodotti}" var="prodotto">
            <tr>
                <th scope="row">${prodotto.codice}</th>
                <td><a href="${context}/admin/Products/show?codice=${prodotto.codice}">${prodotto.nome}</a></td>
                <td>${prodotto.categoria}</td>
                <td>${prodotto.prezzo}€</td>
                <td><a href="${context}/images/${prodotto.copertina}">${prodotto.copertina}</a></td>
                <td>${prodotto.quantita}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function() {
        $('#aggiungi').click(function(){
            window.location.href = '${context}/admin/Products/AddProduct';
        });
    });
</script>
</body>
</html>
