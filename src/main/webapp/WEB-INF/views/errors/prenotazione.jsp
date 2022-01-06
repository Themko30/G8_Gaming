<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Prenotazione Fallita"/>
  </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container text-center my-5">
  <p class="h1" style="color: red;">La tua prenotazione non è stata effettuata correttamente.</p>
  <p class="h2">Controlla i dati inseriti. Ricorda che la copertina deve avere una larghezza compresa tra 800 e 1300 pixel e un'altezza compresa tra 1000 e 1600 pixel.</p>
  <p class="h3"><a href="${context}/prenotazione/">Riprova</a></p>
</div>
</body>
</html>
