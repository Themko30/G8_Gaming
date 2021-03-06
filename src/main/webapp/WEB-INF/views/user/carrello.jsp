<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Carrello"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/header.jsp"%>
<div class="container-xxl my-5">
    <c:choose>
        <c:when test="${carrello.prodotti.isEmpty()}">
            <p class="h2 text-center my-5">Carrello vuoto</p>
        </c:when>
        <c:otherwise>
            <div class="text-center my-2">
                <span>Totale: €${carrello.totale}</span>
                <a href="${context}/cart/confirmOrder"><button type="button" class="btn mx-2">Procedi all'ordine</button></a>
            </div>

            <table class="table table-bordered border-secondary table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Prodotto</th>
                    <th scope="col">Quantità</th>
                    <th scope="col">Prezzo</th>
                    <th scope="col">Rimuovi</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${carrello.prodotti.keySet()}" var="prodotto">
                    <tr>
                        <c:set var="quantita" value="${carrello.prodotti.get(prodotto)}"/>

                        <th scope="row"><a href="${context}/Prodotto/Visualizza?prodotto=${prodotto.codice}">${prodotto.nome}</a></th>
                        <td><form method="post" action="${context}/cart/Modifica" >
                            <input type="hidden" value="${prodotto.codice}" name="prodotto" required>
                            <input type="hidden" value="${quantita+1}" name="quantita" required>
                            <button type="submit" class="btn" style="width: 24px; height: 24px; padding: 1px">+</button>
                        </form>
                            <span> ${quantita}</span>
                            <form method="post" action="${context}/cart/Modifica" >
                                <input type="hidden" value="${prodotto.codice}" name="prodotto" required>
                                <input type="hidden" value="${quantita-1}" name="quantita" required>
                                <button type="submit" class="btn" style="width: 24px; height: 24px; padding: 1px">-</button>
                            </form>
                        </td>
                        <td><span class="total">€${prodotto.prezzoScontato}</span></td>
                        <td><form method="post" action="${context}/cart/Rimuovi">
                            <input type="hidden" name="prodotto" value="${prodotto.codice}" required>
                            <button type="submit" class="btn btn-secondary">Rimuovi</button>
                        </form></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
