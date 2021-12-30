<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-expand-lg navbar-light py-0" id="header" style="background-color: #6e00ee;">
  <div class="container-fluid" id="header-container">
    <a class="navbar-brand" href="#">
      <img src="${context}/icons/favicon.png" alt="logo" width="72" height="72" class="d-inline-block align-text-top"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/admin/"><i class="bi bi-house-fill"></i> Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/admin/Products"><i class="bi bi-dpad-fill"></i> Prodotti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/admin/Orders"><i class="bi bi-bag-check-fill"></i> Ordini</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/admin/Users"><i class="bi bi-people-fill"></i> Utenti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" style="color: var(--d3light);" href="${context}/admin/Booking"><i class="bi bi-bookmark-fill"></i> Prenotazioni</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Cerca" aria-label="Search">
        <button class="btn btn-outline-light" type="submit"><i class="bi bi-search"></i></button>
      </form>
      <ul class="navbar-nav mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" style="color: var(--d3light);" href="${context}/account/logout"><i class="bi bi-box-arrow-left"></i> Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
