<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Ordine Completato"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container text-center my-5">
    <p class="h1">La tua prenotazione è stato effettuata con successo!</p>
    <p class="h3"><a href="${context}/">Torna alla home</a></p>
</div>
</body>
</html>
