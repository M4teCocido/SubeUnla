<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "/views/header.jsp" %>

<div class="container">
	<h4 class="center-align">Ingreso de fichadas</h4>
	<div class="row">
		<div class="input-field col s12 m6 l4">
          	<input placeholder="Numero" id="first_name" type="text" class="validate">
          	<label for="first_name">Numero de Tarjeta SUBE</label>
        </div>
		<div class="input-field col s12 m6 l4">
		    <select>
				<option class="black-text text-lighten-3" value="" disabled selected>Elija tipo de operacion</option>
				<option class="black-text text-lighten-3" value="3">Carga</option>
				<option class="black-text text-lighten-3" value="1">Colectivo</option>
				<option class="black-text text-lighten-3" value="2">Subte</option>
				<option class="black-text text-lighten-3" value="3">Tren</option>
		    </select>
	    	<label>Tipo operacion</label>
  		</div>
  		<div class="input-field col s12 m6 l4">
		    <select>
				<option class="black-text text-lighten-3" value="" disabled selected>Elija una estacion o interno</option>
				<option class="black-text text-lighten-3" value="1">Constitucion</option>
				<option class="black-text text-lighten-3" value="2">Avellaneda</option>
				<option class="black-text text-lighten-3" value="3">Lanus</option>
		    </select>
	    	<label>Estacion o Interno</label>
  		</div>
  		<div class="input-field col s12 m6 l4">
		    <select>
				<option class="black-text text-lighten-3" value="" disabled selected>Elija una lectora</option>
				<option class="black-text text-lighten-3" value="1">Lectora1</option>
				<option class="black-text text-lighten-3" value="2">Lectora2</option>
				<option class="black-text text-lighten-3" value="3">Lectora3</option>
		    </select>
	    	<label>Lectora</label>
  		</div>
  		<div class="input-field col s12 m6 l4">
  			<input placeholder="Fecha de la fichada" id="fecha" type="text" class="datepicker">
          	<label for="first_name">Fecha de la fichada</label>
        </div>
        <div class="input-field col s12 m6 l4">
  			<input placeholder="Hora de la fichada" id="hora" type="text" class="timepicker">
          	<label for="first_name">Hora de la fichada</label>
        </div>
  		
  		
	</div>
</div>
<%@ include file = "/views/footer.jsp" %>