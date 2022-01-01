<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Prenotazioni"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container-xxl my-5">
    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Email</th>
            <th scope="col">Gioco</th>
            <th scope="col">Copertina</th>
            <th scope="col">Accettata</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><a href="#">1</a></th>
            <td>giulio.enzo@gmail.com</td>
            <td>Red Dead Redemption 2</td>
            <td><a href="#">rdd2.jpg</a></td>
            <td>No</td>
        </tr>
        <tr>
            <th scope="row"><a href="#">2</a></th>
            <td>giulio.enzo@gmail.com</td>
            <td>Red Dead Redemption 2</td>
            <td><a href="#">rdd2.jpg</a></td>
            <td>No</td>
        </tr>
        <tr>
            <th scope="row"><a href="#">3</a></th>
            <td>giulio.enzo@gmail.com</td>
            <td>Red Dead Redemption 2</td>
            <td><a href="#">rdd2.jpg</a></td>
            <td>Sì</td>
        </tr>
        <tr>
            <th scope="row"><a href="#">4</a></th>
            <td>giulio.enzo@gmail.com</td>
            <td>Red Dead Redemption 2</td>
            <td><a href="#">rdd2.jpg</a></td>
            <td>Sì</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
