<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/views/header.jsp" %>

<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<br>
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
<div class="modal" id="infoModal">
	<div class="modal-content">
   		<h4 align="center" id="headerModal">AGUARDE UN INSTANTE</h4>
   		<p align="center">El Gobierno Nacional subsidia las tarifas para viajar en</p>
   		<p align="center">trenes y colectivos con SUBE</p>
   		<p align="center">	Beneficiarios de la Tarifa Social Federal:</p>
   		<p align="center">-Jubilados y pensionados. -Ex combatientes de malvinas.</p>
   		<p align="center">-Personal del trabajo domestivo.</p>
   		<p align="center">-Asignacion Universal por Hijo o Embarazo.</p>
   		<p align="center">-Planes Progresar Argentina Trabaja y Ellas Hacen.</p>
   		<p align="center">-Pensiones no contributivas. -Monotributistas Social.</p>
	</div>
	<div class="modal-footer">
   		<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
	</div>
</div>
<%@ include file="/views/footer.jsp" %>

</body>
</html>