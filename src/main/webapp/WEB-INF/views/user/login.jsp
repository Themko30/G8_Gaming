<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <jsp:include page="../partials/head.jsp">
            <jsp:param name="title" value="Login"/>
            <jsp:param name="styles" value="login"/>
        </jsp:include>
    </head>
    <body>
        <div class="center">
            <h1>Login</h1>
            <form method="post" onsubmit="return validate()" action="">
                <div class="txt_field">
                    <input type="text" name="username" id="username" required>
                    <span></span>
                    <label>Username</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="password" id="password" required>
                    <span></span>
                    <label>Password</label>
                </div>
                <div id="liveAlertPlaceholder"></div>
                <input type="submit" value="Login">
                <div class="signup_link">
                    Non hai un account? <a href="http://localhost:8080/G8_Gaming_war_exploded/registrazione">Registrati</a>
                </div>
            </form>
        </div>
    <script>
        function validate() {
            let username = $('#username').val();
            let password = $('#password').val();
            let message = "<ul>";
            let valid = true;
            let regex = /^[a-zA-Z0-9]+$/

            if(!regex.test(username)) {
                message += "<li>L'username non rispetta il formato adatto.</li>";
                valid = false;
            }
            if(username.length < 3 || username.length > 20) {
                message += "<li>L'username deve avere una lunghezza compresa tra i 3 e i 20 caratteri.</li>";
                valid = false;
            }
            if(password.length < 6 || password.length > 64) {
                message += "<li>La password deve avere una lunghezza compresa tra i 2 e i 32 caratteri.</li>";
                valid = false;
            }

            if(!valid) {
                message += "</ul>";
                alertBox(message, "danger");
            }
            return valid;
        }
    </script>
    </body>
</html>
