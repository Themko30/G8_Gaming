<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Giochi per ${piattaforma}"/>
    <jsp:param name="styles" value="home"/>
  </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container">
  <p class="h1 text-center my-3">Videogiochi per ${piattaforma}</p>

  <div class="row my-2">
  <c:forEach items="${prodotti}" var="prodotto">
    <div class="col-sm m-3">
      <div class="card text-center" id="${prodotto.codice}" style="width: 12rem;">
        <img src="${context}/images/${prodotto.copertina}" class="card-img-top p-2" alt="${prodotto.copertina}">
        <div class="card-body">
          <h5 class="card-title">${prodotto.nome}</h5>
          <h6 class="card-subtitle text-muted">${prodotto.categoria}</h6>
        </div>
      </div>
    </div>
  </c:forEach>
  </div>
</div>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
