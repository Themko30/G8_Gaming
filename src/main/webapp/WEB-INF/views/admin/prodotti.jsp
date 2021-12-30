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
        <tr>
            <th scope="row">1</th>
            <td><a href="#">Demon's Souls</a></td>
            <td>RPG</td>
            <td>39.99€</td>
            <td><a href="#">demons_souls_ps5.jpg</a></td>
            <td>23</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td><a href="#">Demon's Souls</a></td>
            <td>RPG</td>
            <td>39.99€</td>
            <td><a href="#">demons_souls_ps5.jpg</a></td>
            <td>23</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td><a href="#">Demon's Souls</a></td>
            <td>RPG</td>
            <td>39.99€</td>
            <td><a href="#">demons_souls_ps5.jpg</a></td>
            <td>23</td>
        </tr>
        <tr>
            <th scope="row">4</th>
            <td><a href="#">Demon's Souls</a></td>
            <td>RPG</td>
            <td>39.99€</td>
            <td><a href="#">demons_souls_ps5.jpg</a></td>
            <td>23</td>
        </tr>
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
