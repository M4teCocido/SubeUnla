<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String title = "Listados"; %>  
<%@ include file = "/views/header.jsp" %>
<script type="text/javascript" src="js/jsListados.js"></script>
</head>
<body>
	<div class="container">
		<div class="section no-pad-bot">
			<div class="row">
				
				<div class="col l1">
					<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Inicio!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
				</div>
				<div class="col l10">
					<h1 class="header center orange-text">Listados</h1>
				</div>
			</div>
			
			<div class="row center">
				<h5 class="header col s12 light">Listados y Resumenes de Viajes y Transacciones</h5>
			</div>
			<br><br>
		</div>
		<div class ="row">
			<div class="input-field col s12 m6 l3">
	  			<input placeholder="Fecha Desde" name="fecha" id="fechaDesde" type="text" class="datepicker">
	          	<label for="fecha">Fecha Desde</label>
	        </div>
	        <div class="input-field col s12 m6 l3">
	  			<input placeholder="Hora Desde" name="hora" id="horaDesde" type="text" class="timepicker">
	          	<label for="hora">Hora Desde</label>
	        </div>
	        <div class="input-field col s12 m6 l3">
	  			<input placeholder="Fecha Hasta" name="fecha" id="fechaHasta" type="text" class="datepicker">
	          	<label for="fecha">Fecha Hasta</label>
	        </div>
	        <div class="input-field col s12 m6 l3">
	  			<input placeholder="Hora Hasta" name="hora" id="horaHasta" type="text" class="timepicker">
	          	<label for="hora">Hora Hasta</label>
	        </div>
        </div>
        <div class ="row">
	        <div class="input-field col s12 m6 l6">
			    <select name="medioTransporte" id="medioTransporte">
					<option class="black-text text-lighten-3" value="0" selected>Todos</option>
					<option class="black-text text-lighten-3" value="1">Colectivo</option>
					<option class="black-text text-lighten-3" value="2">Subte</option>
					<option class="black-text text-lighten-3" value="3">Tren</option>
			    </select>
		    	<label for="medioTransporte">Medio de Transporte</label>
	  		</div>

	  		<div class="input-field col s12 m6 l6" id="divLinea">
			    <select name="linea" id="linea">
					<option class="black-text text-lighten-3" value="0" selected>Todas</option>
			    </select>
		    	<label for="linea">Linea</label>
	  		</div>
        </div>
		<div class="row">
  			<div class="l4">
  				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="procesar">Enviar<i class="material-icons right">send</i>
	  			</button>
  			</div>
  		</div>
		<table id="tabla" class="stripped bordered highlight">	        
		</table>
	</div>

<%@ include file = "/views/footer.jsp" %>

</body>
</html>