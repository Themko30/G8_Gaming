<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>500 Internal Server Error - D3Games</title>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <link rel="icon" href="${context}/icons/favicon.png"/>
    <link rel="stylesheet" href="${context}/css/library.css">
    <link rel="stylesheet" href="${context}/css/errorPage.css">
</head>
<body>
<div id="main" onclick="window.history.back()">
    <div class="err">
        <h1>
            Errore 500 <br>
            <span>Spiacenti, si è verificato un errore nel server</span> <br>
            <span>Clicca per tornare indietro</span>
        </h1>
    </div>
</div>
</body>
</html>
