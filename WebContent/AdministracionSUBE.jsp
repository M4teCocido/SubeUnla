<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String title = "Fichadas"; %>    
<%@ include file = "/views/header.jsp" %>
</head>
<body>
<div class="container">
	<div class="section no-pad-bot">
		<br>
		<div class="row">
			<div class="col l1">
				<a class="btn-floating tooltipped btn-large waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
			</div>
			<div class="col l10">
				<h1 class="header center orange-text">ABM SUBE</h1>
			</div>
		</div>
		
		<div class="row center">
			<h5 class="header col s12 light">Alta, Baja y Modificaciones de Tarjetas SUBE</h5>
		</div>
		<br><br>
	</div>
	<div class="row">
		<div class="input-field col s12 m6 l4">
          	<input placeholder="Numero" name="numerotarjeta" id="numerotarjeta" type="text" class="validate" data-length="16"></input>
          	<label for="numerotarjeta">Numero de Tarjeta SUBE</label>
        </div>
  		<div class="input-field col s12 m6 l4" id="divsaldo">
          	<input placeholder="Saldo" name="saldo" id="saldo" type="text" class="validate">
          	<label for="saldo">Ingrese saldo de Tarjeta</label>
        </div>
  		<div class="row">
  			<div class="l4">
  				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="guardarTarjeta">Guardar<i class="material-icons right">Guardar</i>
  				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="anularTarjeta">Anular<i class="material-icons right">Anular</i>
	  			</button>
  			</div>
  		</div>
	</div>
</div>
<script type="text/javascript" src="js/jsIngresarFichada.js"></script>
<%@ include file = "/views/footer.jsp" %>