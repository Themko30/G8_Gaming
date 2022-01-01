<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Utenti"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container-xxl my-5">
    <table class="table table-bordered border-secondary table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Nome</th>
            <th scope="col">Email</th>
            <th scope="col">Sesso</th>
            <th scope="col">Admin</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><a href="#">g.donisi1</a></th>
            <td>Giovanni Donisi</td>
            <td>g.donisi1@studenti.unisa.it</td>
            <td>Maschio</td>
            <td><button type="button" class="btn" style="background-color: var(--primary); color: var(--d3light);" disabled>Eleggi</button></td>
        </tr>
        <tr>
            <th scope="row"><a href="#">giulio01</a></th>
            <td>Giulio Enzo</td>
            <td>giulio.enzo@gmail.com</td>
            <td>Maschio</td>
            <td><button type="button" class="btn" style="background-color: var(--primary); color: var(--d3light);">Eleggi</button></td>
        </tr>
        <tr>
            <th scope="row"><a href="#">giulio01</a></th>
            <td>Giulio Enzo</td>
            <td>giulio.enzo@gmail.com</td>
            <td>Maschio</td>
            <td><button type="button" class="btn" style="background-color: var(--primary); color: var(--d3light);">Eleggi</button></td>
        </tr>
        <tr>
            <th scope="row"><a href="#">giulio01</a></th>
            <td>Giulio Enzo</td>
            <td>giulio.enzo@gmail.com</td>
            <td>Maschio</td>
            <td><button type="button" class="btn" style="background-color: var(--primary); color: var(--d3light);">Eleggi</button></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
