<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "/views/header.jsp" %>

<div class="container">
	<h4 class="center-align">Ingreso de fichadas</h4>
	<div class="row">
		<div class="input-field col s12 m6 l4">
          	<input placeholder="Numero" name="numerotarjeta" id="numerotarjeta" type="text" class="validate" data-length="16"></input>
          	<label for="numerotarjeta">Numero de Tarjeta SUBE</label>
        </div>
  		<div class="input-field col s12 m6 l4">
  			<input placeholder="Fecha de la fichada" name="fecha" id="fecha" type="text" class="datepicker">
          	<label for="fecha">Fecha de la fichada</label>
        </div>
        <div class="input-field col s12 m6 l4">
  			<input placeholder="Hora de la fichada" name="hora" id="hora" type="text" class="timepicker">
          	<label for="hora">Hora de la fichada</label>
        </div>
        <div class="input-field col s12 m6 l4">
		    <select name="tipotransaccion" id="tipotransaccion">
				<option class="black-text text-lighten-3" value="" disabled selected>Elija tipo de transaccion</option>
				<option class="black-text text-lighten-3" value="1">Carga</option>
				<option class="black-text text-lighten-3" value="2">Colectivo</option>
				<option class="black-text text-lighten-3" value="3">Subte</option>
				<option class="black-text text-lighten-3" value="4">Tren</option>
		    </select>
	    	<label for="tipotransaccion">Tipo operacion</label>
  		</div>
  		<div class="input-field col s12 m6 l4" id="divlinea">
		    <select name="linea" id="linea">
				<option class="black-text text-lighten-3" value="" disabled selected>Elija una linea</option>
		    </select>
	    	<label for="linea">Linea</label>
  		</div>
  		<div class="input-field col s12 m6 l4" id="divestacioninterno">
		    <select name="estacioninterno" id="estacioninterno">
				<option class="black-text text-lighten-3" value="" disabled selected>Elija una estacion o interno</option>
		    </select>
	    	<label for="estacioninterno">Estacion o Interno</label>
  		</div>
  		<div class="input-field col s12 m6 l4" id="divlectora">
		    <select name="lectora" id="lectora">
				<option class="black-text text-lighten-3" value="" disabled selected>Elija una lectora</option>
		    </select>
	    	<label for="lectora">Lectora</label>
  		</div>
  		<div class="input-field col s12 m6 l4" id="divtramo">
		    <select name="tramo" id="tramo">
				<option class="black-text text-lighten-3" value="" disabled selected>Elija el tramo</option>
		    </select>
	    	<label for="tramo">tramo</label>
  		</div>
  		<div class="input-field col s12 m6 l4" id="divmonto">
          	<input placeholder="Monto" name="monto" id="monto" type="text" class="validate">
          	<label for="monto">Ingrese monto de carga</label>
        </div>
  		<div class="row">
	  		<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="enviarfichada">Enviar
	    		<i class="material-icons right">send</i>
	  		</button>
  		</div>
	</div>
</div>
<script type="text/javascript" src="js/jsIngresarFichada.js"></script>

<%@ include file = "/views/footer.jsp" %>