<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "/views/header.jsp" %>
</head>
<body>
<div class="container">
	<div class="section no-pad-bot" id="index-banner">
		<div class="row">
			<div class="col l1">
				<br><br>
				<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
			</div>
			<div class="col l10">
				<h1 class="header center orange-text">Registro de tarjeta.</h1>
			</div>
		</div>
		<div class="row center">
			<h5 class="header col s12 light">Registrate para consultar y recuperar tu saldo, ver tus movimientos o denunciar un Punto SUBE.</h5>
		</div>
		<br><br>
	</div>
	<div class="row">
		<div class="input-field col s4 m4 l4">
          <input placeholder="Numero de tarjeta" id="nroTarjeta" type="text" class="validate" />
          <label for="nroTarjeta">Numero de Tarjeta</label>
        </div>
        <div class="input-field col s4 m4 l4">
          <input placeholder="Nombre" id="nombre" type="text" class="validate" />
          <label for="nombre">Nombre</label>
        </div>
        <div class="input-field col s4 m4 l4">
			<input placeholder="Apellido" id="apellido" type="text" class="validate" />
			<label for="apellido">Apellido</label>
		</div>
	</div>
	<div class="row">
		<div class="input-field col s4 m4 l4">
			<select id="genero">
				<option value="" disabled selected>Seleccione una opcion</option>
      			<option value="1">Femenino</option>
		        <option value="2">Masculino</option>
    		</select>
    		<label for="genero">Genero</label>
		</div>
		<div class="input-field col s4 m4 l4">
			<select id="tipoDoc">
				<option value="" disabled selected>Seleccione una opcion</option>
      			<option value="1">DNI - Documento Nacional de Identidad</option>
		        <option value="2">LE - Libreta de Enrolamiento</option>
    		</select>
    		<label for="tipoDoc">Tipo de Documento</label>
		</div>
      	<div class="input-field col s4 m4 l4">
			<input placeholder="Numero de Documento" id="nroDocumento" type="text" class="validate" data-lenght="8" />
			<label for="nroDocumento">Numero de documento</label>
		</div>
	</div>
	<div class="row">
		<div class="input-field col s2 m2 l2">
			<select placeholder="Año" name="year" id="year" type="text">
			    <option value="" disabled selected>Año</option>
			</select>
          	<label for="year">Fecha de nacimiento</label>
		</div>
		<div class="input-field col s2 m2 l2">
			<select placeholder="Mes" name="month" id="month" type="text">
			    <option value="" disabled selected>Mes</option>
			</select>
		</div>
		<div class="input-field col s2 m2 l2">
			<select placeholder="Dia" name="day" id="day" type="text">
			    <option value="" disabled selected>Dia</option>
			</select>
		</div>
		<div class="input-field col s6 m6 l6">
          	<input placeholder="Email" id="email" type="email" class="validate" />
          	<label for="email">Email</label>
        </div>
	</div>
	<div class="row">
        <div class="input-field col s4 m4 l4">
          	<input placeholder="Celular" id="celular" type="text" class="validate" />
          	<label for="celular">Celular</label>
        </div>
        <div class="input-field col s4 m4 l4">
          	<input placeholder="Telefono" id="telefono" type="text" class="validate" />
          	<label for="telefono">Telefono</label>
        </div>
        <div class="input-field col s4 m4 l4">
			<select id="descEstudiantil">
				<option value="0">No posee</option>
				<option value="1">Escolar</option>
        		<option value="2">Universitario</option>
			</select>
			<label for="descEstudiantil">Descuento Estudiantil</label>
		</div>
	</div>
	<div class="row">
		<div class="input-field col s4 m4 l4">
          	<input placeholder="Contraseña" id="password" type="password" class="validate" data-lenght="4" />
          	<label for="password">Contraseña</label>
        </div>
        <div class="input-field col s4 m4 l4">
          	<input placeholder="Contraseña" id="passwordRepeat" type="password" class="validate" data-lenght="4" />
          	<label for="passwordRepeat">Repetir Clave</label>
        </div>
        <div class="col s4 m4 l4">
        	<br>
        	<label class="valign-wrapper center-align">
				<input id="tarifaSocial" type="checkbox" class="filled-in" />
				<span>Tarifa Social</span>
			</label>
        </div>
	</div>
	<div class="center-align row">
		<div class="col l6">
			<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="limpiarForm">Limpiar Formulario<i class="material-icons right">clear</i>
  			</button>
		</div>
		<div class="col l6">
			<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="enviarRegistro">Enviar<i class="material-icons right">send</i>
	  		</button>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="js/jsRegistroSube.js"></script>

<%@ include file = "/views/footer.jsp" %>