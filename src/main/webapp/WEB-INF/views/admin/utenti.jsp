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
        <c:forEach items="${utenti}" var="utente">
            <tr>
                <th scope="row"><a href="${context}/admin/Users/Show?username=${utente.username}">${utente.username}</a></th>
                <td>${utente.nome} ${utente.cognome}</td>
                <td>${utente.email}</td>
                <td>${utente.sesso}</td>
                <td><button type="button" class="btn" style="background-color: var(--primary); color: var(--d3light);"
                        <c:if test="${utente.admin}"> disabled</c:if>>Eleggi</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Eleggi ad amministratore</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
                    <form action="${context}/admin/Users/SetAdmin" method="post">
                        <input type="hidden" name="username" id="username" required>
                        <button type="submit" class="btn" style="background-color: var(--primary); color: var(--d3light);">Sì, eleggi</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        let username = "";
        $(".table").on('click', '.btn', function(){
            let row = $(this).closest("tr"); // Seleziona la riga in cui si trova il bottone
            username = row.find("th:eq(0)").text();// Trova l'username
            let nome = row.find("td:eq(0)").text();// Trova il nome
            $('.modal-body').text("Sei sicuro di voler eleggere " + nome + " (" + username + ") come amministratore?");
            $('#username').val(username);
            $('#confirmModal').modal('show');
        });
    });
</script>
</body>
</html>
