<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Admin"/>
        <jsp:param name="styles" value="admin"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin_header.jsp"%>
<div class="container my-5">
    <div class="row">
        <div class="col-sm my-3">
            <div class="card border-secondary text-center" id="Products" style="width: 18rem;">
                <div class="card-header">Prodotti in Magazzino</div>
                <div class="card-body">
                    <h3 class="card-title">53</h3>
                </div>
            </div>
        </div>

        <div class="col-sm my-3">
            <div class="card border-secondary text-center" id="Users" style="width: 18rem;">
                <div class="card-header">Utenti Registrati</div>
                <div class="card-body">
                    <h3 class="card-title">223</h3>
                </div>
            </div>
        </div>
        <div class="col-sm my-3">
            <div class="card border-secondary text-center" id="Orders" style="width: 18rem;">
                <div class="card-header">Ordini Effettuati</div>
                <div class="card-body">
                    <h3 class="card-title">94</h3>
                </div>
            </div>
        </div>
        <div class="col-sm my-3">
            <div class="card border-secondary text-center" id="Booking" style="width: 18rem;">
                <div class="card-header">Prenotazioni Ricevute</div>
                <div class="card-body">
                    <h3 class="card-title">7</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('.card').click(function(){
            window.location.href = '${context}/admin/' + this.attr("id");
        });
    });
</script>
</body>
</html>
