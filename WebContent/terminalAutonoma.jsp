<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/views/header.jsp" %>

<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<div class="row">
			<div class="col l1">
				<br><br>
				<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
			</div>
			<div class="col l10">
				<h1 class="header center orange-text">Terminal Autonoma</h1>
			</div>
		</div>
		<div class="row center">
			<h5 class="header col s12 light">Ingrese el numero de tarjeta:</h5>
		</div>
		<br>
		<div class="row">
			<div class="input-field col s8 m8 l8">
          		<input placeholder="Si si, aqui..." name="nroTarjeta" id="nroTarjeta" type="text" class="validate" data-length="16"></input>
          	<label for="nroTarjeta">Aqui abajo</label>
	        </div>
	        <div class="col l4">
	        	<br>
				<button class="valign-wrapper btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="consultarTarjeta">Consultar<i class="material-icons right">send</i>
		  		</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jsTerminalAutonoma.js"></script>
<%@ include file="/views/footer.jsp" %>
</body>
</html>