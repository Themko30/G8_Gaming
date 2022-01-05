<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Conferma Ordine"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>

<div class="container d-flex justify-content-center align-items-center my-5">
    <div class="col-lg-8">
        <div class="card mb-4">
            <div class="card-body">
                <form method="post" onsubmit="return checkCampi()" action="">
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Indirizzo di spedizione: </p>
                        </div>
                        <div class="col-sm-9">
                            <div class="form-floating">
                                <input type="text" class="form-control" name="indirizzoSpedizione" id="indirizzoSpedizione" value="${utente.indirizzo}">
                                <label for="indirizzoSpedizione">Scegli dove ricevere il gioco</label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Metodo di pagamento: </p>
                        </div>
                        <div class="col-sm-9">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="metodoPagamento" value="PayPal" id="metodoPagamento1" checked>
                                <label class="form-check-label" for="metodoPagamento1">
                                    <i class="bi bi-paypal"></i> PayPal
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="metodoPagamento" value="Carta di credito" id="metodoPagamento2">
                                <label class="form-check-label" for="metodoPagamento2">
                                    <i class="bi bi-credit-card"></i> Carta di credito
                                </label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Totale: €${carrello.totale}</p>
                        </div>
                        <div class="col-sm-9">
                            <button type="submit" class="btn">Conferma</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
        <div id="liveToast" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    Campi non validi!
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</div>
<script>
    function checkCampi() {
        let indirizzo = $('#indirizzoSpedizione').val();
        let metodo = $("input[name='metodoPagamento']:checked").val();
        regex = /[A-Za-z ]+[,][ ]?[0-9A-Za-z]+/;
        valid = true;

        if(!regex.test(indirizzo)) {
            valid = false;
        }
        if(indirizzo.length < 4 || indirizzo.length > 64) {
            valid = false;
        }
        if(metodo !== 'PayPal' && metodo !== 'Carta di credito') {
            valid = false;
        }
        if(!valid) {
            $('#liveToast').toast("show");
        }
        return valid;
    }
</script>
<%@include file="../partials/footer.jsp"%>
</body>
</html>
