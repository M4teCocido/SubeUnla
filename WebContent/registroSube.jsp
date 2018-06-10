<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "/views/header.jsp" %>

<div class="container">
	<div class="section no-pad-bot" id="index-banner">
		<br>
		<a class="btn-floating tooltipped btn-large waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
		<h1 class="header center orange-text">Registro de Tarjeta</h1>
		<div class="row center">
			<h5 class="header col s12 light">Registrate para consultar y recuperar tu saldo, ver tus movimientos o denunciar un Punto SUBE.</h5>
		</div>
		<br><br>
	</div>
	<div class="row">
		<div class="input-field col s4 m4 l4">
          <input placeholder="Numero de tarjeta" id="nroTarjeta" type="text" class="validate">
          <label for="nroTarjeta">Numero de Tarjeta</label>
        </div>
        <div class="input-field col s4 m4 l4">
          <input placeholder="Nombre" id="nombre" type="text" class="validate">
          <label for="nombre">Nombre</label>
        </div>
        <div class="input-field col s4 m4 l4">
			<input placeholder="Apellido" id="apellido" type="text" class="validate">
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
		        <option value="2">DE - Documento Extranjero</option>
    		</select>
    		<label for="tipoDoc">Tipo de Documento</label>
		</div>
      	<div class="input-field col s4 m4 l4">
			<input placeholder="Numero de Documento" id="nroDocumento" type="text" class="validate">
			<label for="nroDocumento">Numero de documento</label>
		</div>
	</div>
	<div class="row">
		<div class="input-field col s3 m3 l3">
			<input placeholder="Fecha de nacimiento" name="fechaDeNacimiento" id="fechaDeNacimiento" type="text" class="datepicker">
          	<label for="fecha">Fecha de nacimiento</label>
		</div>
		<div class="input-field col s3 m3 l3">
          <input placeholder="Email" id="email" type="text" class="validate">
          <label for="email">Email</label>
        </div>
        <div class="input-field col s3 m3 l3">
          <input placeholder="Celular" id="celular" type="text" class="validate">
          <label for="celular">Celular</label>
        </div>
        <div class="input-field col s3 m3 l3">
          <input placeholder="Telefono" id="telefono" type="text" class="validate">
          <label for="telefono">Telefono</label>
        </div>
	</div>
	<div class="row">
		<div class="center-align col s6 m6 l6">
			<label>
   				<input id="descTarifaSocial" type="checkbox" class="filled-in" />
     			<span>Descuento tarifa social</span>
   			</label>
		</div>
		<div class="center-align col s6 m6 l6">
      		<label>
			    <input id="descEstudiantil" type="checkbox" class="filled-in" />
        		<span>Descuento boleto estudiantil</span>
      		</label>
		</div>
	</div>
</div>

<%@ include file = "/views/footer.jsp" %>