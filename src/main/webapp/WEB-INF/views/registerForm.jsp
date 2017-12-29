<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
	<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- Stylesheets -->
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../../../../../../Library_managment_system-jsp-test/src/main/webapp/web-resources/libstyle.css">
		<title>Strona rejestracji</title>
	</head>
	<body>
		<div class="container">

		<div class="page-header text-center">
			<h1>Strona rejestracji biblioteki</h1>
		</div>

		<div id="registerForm" class="jumbotron jumbotron-form">
			<form method="POST">
					Imię<br>
					<input type="text" name="name">
					<br>
					Nazwisko<br>
					<input type="text" name="surname">
					<br>
					Nazwa użytkownika<br>
					<input type="text" name="username">
					<br>
					Hasło<br>
					<input type="password" name="password">
					<br>
					<input type="submit" class="btn btn-success btn-login" value="Zarejestruj">
				</form>
			</div>
		</div>


		<footer class="footer">
			<div class="container container-footer">
				<span class="text-muted">Projekt IP 2017 © LibMS</span>
			</div>
		</footer>
		
		
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
		</script>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous">
		</script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous">
		</script>
