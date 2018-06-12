<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "/views/header.jsp" %>
<script type="text/javascript" src="js/Chart.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="section no-pad-bot">
			<div class="row">
				<div class="col l2">
					<br>
					<br>
					<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
				</div>
				<div class="col l8">
					<h3 class="header center orange-text">Estadisticas</h3>
				</div>
			</div>
			<div class="row center">
				<h5 class="header col s12 light">Tablas, grafico circular y barras.</h5>
			</div>
		</div>
		
		<!-- INICIO DE CIRCULARES -->
		
		<div class="section"><h4 class="header center">Circular</h4></div>
		<div class="row">
			<div class="input-field col l4">
				<select id="medioCircular">
					<option value="" disabled selected>Seleccione una opcion</option>
 					<option value="1">Colectivo</option>
	        		<option value="2">Tren</option>
	        		<option value="2">Subte</option>
   				</select>
   				<label for="medioCircular">Medio de Transporte</label>
			</div>
			<div class="input-field col l2">
				<input placeholder="Desde" name="desdeCircular" id="desdeCircular" type="text" class="datepicker">
          		<label for="desdeCircular">Desde el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaDesdeCircular" id="horaDesdeCircular" type="text" class="timepicker">
	          	<label for="horaDesdeCircular">A la hora...</label>
	        </div>
			<div class="input-field col l2">
				<input placeholder="Hasta" name="hastaCircular" id="hastaCircular" type="text" class="datepicker">
          		<label for="hastaCircular">Hasta el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaHastaCircular" id="horaHastaCircular" type="text" class="timepicker">
	          	<label for="horaHastaCircular">A la hora...</label>
	        </div>
			<div class="input-field col l4">
				<select id="lineaCircular">
					<option value="" disabled selected>Seleccione una opcion</option>
   				</select>
   				<label for="lineaCircular">Linea</label>
			</div>
			
			<div class="input-field col l4">
				<select id="tramoCircular">
					<option value="" disabled selected>Seleccione una opcion</option>
   				</select>
   				<label for="tramoCircular">Tramo (opcional)</label>
   			</div>
   			<div class="col l4">
   				<br>
				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="consultaCircular">Consultar<i class="material-icons right">send</i>
	  		</button>
		</div>
		</div>
		<div class="row">
			<div class="col l6">
				<canvas id="circularPorLinea" width="400" height="400"></canvas>
			</div>
			<div class="col l6">
				<canvas id="circularPorMonto" width="400" height="400"></canvas>
			</div>
		</div>

		<!-- INICIO DE BARRAS -->

		<div class="section"><h4 class="header center">Barra</h4></div>
		<div class="row">
			<div class="input-field col l4">
				<select id="medioBarra">
					<option value="" disabled selected>Seleccione una opcion</option>
 					<option value="1">Colectivo</option>
	        		<option value="2">Tren</option>
	        		<option value="2">Subte</option>
   				</select>
   				<label for="medioBarra">Medio de Transporte</label>
			</div>
			<div class="input-field col l2">
				<input placeholder="Desde" name="desdeBarra" id="desdeBarra" type="text" class="datepicker">
          		<label for="desdeBarra">Desde el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaDesdeBarra" id="horaDesdeBarra" type="text" class="timepicker">
	          	<label for="horaDesdeBarra">A la hora...</label>
	        </div>
			<div class="input-field col l2">
				<input placeholder="Hasta" name="hastaBarra" id="hastaBarra" type="text" class="datepicker">
          		<label for="hastaBarra">Hasta el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaHastaBarra" id="horaHastaBarra" type="text" class="timepicker">
	          	<label for="horaHastaBarra">A la hora...</label>
	        </div>
			<div class="input-field col l4">
				<select id="lineaBarra">
					<option value="" disabled selected>Seleccione una opcion</option>
   				</select>
   				<label for="lineaBarra">Linea</label>
			</div>
			
			<div class="input-field col l4">
				<select id="tramoBarra">
					<option value="" disabled selected>Seleccione una opcion</option>
   				</select>
   				<label for="tramoBarra">Tramo (opcional)</label>
   			</div>
   			
   			<div class="col l4">
   				<br>
				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="consultaBarra">Consultar<i class="material-icons right">send</i>
	  		</button>
		</div>
		<div class="row">
			<div class="col l6">
				<canvas id="barraPorLinea" width="400" height="400"></canvas>
			</div>
			<div class="col l6">
				<canvas id="barraPorMonto" width="400" height="400"></canvas>
			</div>
		</div>

		<!-- INICIO DE TABLAS -->

		<div class="section"><h4 class="header center">Tablas</h4></div>
		<div class="row">
			<div class="input-field col l4">
				<select id="medioTabla">
					<option value="" disabled selected>Seleccione una opcion</option>
 					<option value="1">Colectivo</option>
	        		<option value="2">Tren</option>
	        		<option value="2">Subte</option>
   				</select>
   				<label for="medioTabla">Medio de Transporte</label>
			</div>
			<div class="input-field col l2">
				<input placeholder="Desde" name="desdeTabla" id="desdeTabla" type="text" class="datepicker">
          		<label for="desdeTabla">Desde el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaDesdeTabla" id="horaDesdeTabla" type="text" class="timepicker">
	          	<label for="horaDesdeTabla">A la hora...</label>
	        </div>
			<div class="input-field col l2">
				<input placeholder="Hasta" name="hastaTabla" id="hastaTabla" type="text" class="datepicker">
          		<label for="hastaTabla">Hasta el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaHastaTabla" id="horaHastaTabla" type="text" class="timepicker">
	          	<label for="horaHastaTabla">A la hora...</label>
	        </div>
			<div class="input-field col l4">
				<select id="lineaTabla">
					<option value="" disabled selected>Seleccione una opcion</option>
   				</select>
   				<label for="lineaTabla">Linea</label>
			</div>
			
			<div class="input-field col l4">
				<select id="tramoTabla">
					<option value="" disabled selected>Seleccione una opcion</option>
   				</select>
   				<label for="tramoTabla">Tramo (opcional)</label>
   			</div>
   			<div class="col l4">
   				<br>
				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="consultaTabla">Consultar<i class="material-icons right">send</i>
	  		</button>
		</div>
		<div class="row">
			<div class="col l6">
				<canvas id="tablaPorLinea" width="400" height="400"></canvas>
			</div>
			<div class="col l6">
				<canvas id="tablaPorMonto" width="400" height="400"></canvas>
			</div>
		</div>
	</div>

<script type="text/javascript" src="js/jsEstadisticas.js"></script>
<%@ include file = "/views/footer.jsp" %>