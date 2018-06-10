<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file = "/views/header.jsp" %>

<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<br><br>
		<h1 class="header center orange-text">Bienvenido a SUBE-UNLA</h1>
		<div class="row center">
			<h5 class="header col s12 light">Trabajo practico Programacion Orientada a Objetos 2</h5>
		</div>
		<br><br>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col s3 center-align">
			<a class="waves-effect waves-light" href="terminalAutonoma.jsp"><i class="large material-icons">tv</i></a>
			<h6>Terminal</h6>
			<p>Consulta informacion de tu SUBE en una terminal autonoma.</p>
		</div>
		<div class="col s3 center-align">
			<a class="waves-effect waves-light" href="registroSube.jsp"><i class="large material-icons">credit_card</i></a>
			<h6>Registrar Tarjeta</h6>
			<p>Registra una tarjeta a tu nombre para poder acceder a los beneficios que te corresponden.</p>
		</div>
		<div class="col s3 center-align">
			<a class="waves-effect waves-light" href="fichada.jsp"><i class="large material-icons">monetization_on</i></a>
			<h6>Fichada</h6>
			<p>Ingresa una carga o una fichada de un transporte publico.</p>
		</div>
		<div class="col s3 center-align">
			<a class="waves-effect waves-light" href="login.jsp"><i class="large material-icons">face</i></a>
			<h6>Login</h6>
			<p>Ingresa con tu usuario y contraseña a nuestro sistema.</p>
		</div>
	</div>
</div>

<%@ include file = "/views/footer.jsp" %>