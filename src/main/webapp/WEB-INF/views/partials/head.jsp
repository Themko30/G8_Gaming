<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>${param.title} - D3Games</title>
<meta name="description" content="E-Commerce di videogiochi">
<link rel="icon" type="image/png" href="${context}/icons/favicon.png"/>
<meta name="viewport" content="height=device-height, width=device-width, initial-scale=1.0, viewport-fit=cover">
<link rel="stylesheet" href="${context}/css/library.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<meta name="theme-color" content="#6e00ee">
<c:if test="${not empty param.styles}">
    <c:forTokens items="${param.styles}" delims="," var="style">
<link rel="stylesheet" href="${context}/css/${style}.css">
    </c:forTokens>
</c:if>
<c:if test="${not empty param.scripts}">
    <c:forTokens items="${param.scripts}" delims="," var="script">
<script src="${context}/js/${script}.js" defer></script>
    </c:forTokens>
</c:if>
