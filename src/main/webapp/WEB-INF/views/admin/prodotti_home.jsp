<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Prodotti in evidenza"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container-xxl my-4">
    <a href="${context}/admin/Products/AddProduct"><button type="button" class="btn mb-4">Nuovo Prodotto</button></a>

    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nome</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Immagine</th>
            <th scope="col">Rimuovi dalla Home</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${home}" var="prodotto">
            <tr>
                <th scope="row">${prodotto.codice}</th>
                <td><a href="${context}/admin/Products/show?codice=${prodotto.codice}">${prodotto.nome}</a></td>
                <td>${prodotto.prezzo}€</td>
                <td><a href="${context}/images/${prodotto.copertina}">${prodotto.copertina}</a></td>
                <td><form method="post" action="${context}/admin/SetHomePage">
                    <input type="hidden" value="${prodotto.codice}" name="codiceProdotto" required>
                    <button type="submit" class="btn">Rimuovi</button>
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
        <div id="liveToast" class="toast align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    Prodotto rimosso dalla homepage!
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</div>
<c:if test="${rimosso == 1}">
    <script>
        $(document).ready(function() {
            $('#liveToast').toast("show");
        });
    </script>
</c:if>
</body>
</html>
