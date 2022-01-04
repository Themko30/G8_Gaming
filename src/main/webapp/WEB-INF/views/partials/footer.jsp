<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<button onclick="topFunction()" id="backtotop" title="Torna su"><i class="bi bi-arrow-bar-up"></i></button>
<footer>
    <div class="footer-content">
        <table>
            <tr>
                <td><img id="footerlogo" src="${context}/icons/favicon.png"></td>
                <td><h3>&nbsp;&nbsp;D3Games</h3></td>
            </tr>
        </table>

        <ul class="socials">
            <li><a href="https://www.instagram.com/unisalerno/" target="_blank"><i id="ig" class="bi bi-instagram"></i></a></li>
            <li><a href="https://twitter.com/unisalerno" target="_blank"><i id="tw" class="bi bi-twitter"></i></a></li>
            <li><a href="https://www.youtube.com/channel/UCi3CdtXjmc68QC4uj6xYP4g" target="_blank"><i id="yt" class="bi bi-youtube"></i></a></li>
            <li><a href="https://www.facebook.com/unisalerno" target="_blank"><i id="fb" class="bi bi-facebook"></i></a></li>
        </ul>
    </div>
    <div class="footer-bottom">
        <p style="margin-bottom: 0.4rem;">Leggi la nostra <a href="${context}/privacyPolicy.jsp" target="_blank">Privacy Policy</a></p>
        <p>Copyright &copy; 2022 D3Games - Tutti i diritti riservati</p>
    </div>
</footer>