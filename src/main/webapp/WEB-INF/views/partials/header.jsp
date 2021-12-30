<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${not empty utente}">
        <c:set var="account" scope="session" value="account/profile"/>
        <c:set var="cart" scope="session" value="account/cart"/>
    </c:when>
    <c:otherwise>
        <c:set var="account" scope="session" value="account/login"/>
        <c:set var="cart" scope="session" value="account/login"/>
    </c:otherwise>
</c:choose>
<nav class="navbar navbar-expand-lg navbar-light py-0" id="header" style="background-color: var(--primary);">
    <div class="container-fluid" id="header-container">
        <a class="navbar-brand" href="${context}/">
            <img src="${context}/icons/favicon.png" alt="logo" width="72" height="72" class="d-inline-block align-text-top"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/"><i class="bi bi-house-fill"></i> Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/categorie"><i class="bi bi-tag-fill"></i> Categorie</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" style="color: var(--d3light);" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-joystick"></i> Piattaforme
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${context}/piattaforma/ps5"><i class="bi bi-playstation"></i> PlayStation 5</a></li>
                        <li><a class="dropdown-item" href="${context}/piattaforma/seriesx"><i class="bi bi-xbox"></i> Xbox Series X|S</a></li>
                        <li><a class="dropdown-item" href="${context}/piattaforma/switch"><i class="bi bi-nintendo-switch"></i> Nintendo Switch</a></li>
                        <li><a class="dropdown-item" href="${context}/piattaforma/pc"><i class="bi bi-pc-display"></i> PC</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${context}/piattaforma/ps4"><i class="bi bi-playstation"></i> PlayStation 4</a></li>
                        <li><a class="dropdown-item" href="${context}/piattaforma/xboxone"><i class="bi bi-xbox"></i> Xbox One</a></li>
                    </ul>
                </li>

            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Cerca" aria-label="Search">
                <button class="btn btn-outline-light" type="submit"><i class="bi bi-search"></i></button>
            </form>
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" style="color: var(--d3light);" href="${context}/${account}"><i class="bi bi-person-fill"></i> Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: var(--d3light);" href="${context}/${cart}"><i class="bi bi-cart-fill"></i> Carrello</a>
                </li>
            </ul>
        </div>
    </div>
</nav>