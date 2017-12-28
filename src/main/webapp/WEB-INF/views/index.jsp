<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="pl">
<head>
    <link rel="shortcut icon"
          href="../../../../../../Library_managment_system-jsp-test/src/main/webapp/web-resources/favicon.png"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Stylesheets -->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="../../../../../../library_application/src/main/webapp/web-resources/libstyle.css">
    <title>Strona główna</title>
</head>
<body>
<div class="container">

    <div class="page-header text-center">
        <h1>Strona główna systemu zarządzania biblioteką</h1>
        <img src="../../../../../../library_application/src/main/webapp/web-resources/favicon.png">
    </div>

    <div class="jumbotron">
        <form action="/login">
            <input type="submit" class="btn btn-success btn-login btn-index" value="Logowanie"/>
        </form>
        <form action="/login">
            <input type="submit" class="btn btn-success btn-login btn-index" value="Rejestracja"/>
        </form>
        <form action="/login">
            <input type="submit" class="btn btn-success btn-login btn-index" value="Przejrzyj zbiór"/>
        </form>

        <form action="/library/register">
            <input type="submit" class="btn btn-success btn-login btn-index" value="Rejestracja"/>
        </form>

    </div>


</div>


<footer class="footer">
    <div class="container container-footer">
        <span class="text-muted">Projekt IP 2017 © LibMS</span>
    </div>
</footer>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>
</body>
</html>

