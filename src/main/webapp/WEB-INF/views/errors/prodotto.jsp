<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Inserimento Fallito"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container text-center my-5">
    <p class="h1" style="color: red;">Il prodotto non è stato inserito con successo.</p>
    <p class="h2">Controlla i campi inseriti e riprova.</p>
    <p class="h3"><a href="${context}/admin/Products/AddProduct">Torna alla pagina di inserimento</a></p>
</div>
</body>
</html>
