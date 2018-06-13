<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "/views/header.jsp" %>
<script type="text/javascript" src="js/Chart.bundle.min.js"></script>
<script type="text/javascript" src="js/jsEstadisticas.js"></script>

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
		
		<div class="row">
			<div class="input-field col l4">
				<select id="medio">
					<option value="" disabled selected>Seleccione una opcion</option>
 					<option value="1">Colectivo</option>
	        		<option value="2">Tren</option>
	        		<option value="3">Subte</option>
   				</select>
   				<label for="medio">Medio de Transporte</label>
			</div>
			<div class="input-field col l2">
				<input placeholder="Desde" name="desde" id="desde" type="text" class="datepicker">
          		<label for="desde">Desde el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaDesde" id="horaDesde" type="text" class="timepicker">
	          	<label for="horaDesde">A la hora...</label>
	        </div>
			<div class="input-field col l2">
				<input placeholder="Hasta" name="hasta" id="hasta" type="text" class="datepicker">
          		<label for="hasta">Hasta el dia...</label>
			</div>
			<div class="input-field col l2">
	  			<input placeholder="Hora" name="horaHasta" id="horaHasta" type="text" class="timepicker">
	          	<label for="horaHasta">A la hora...</label>
	        </div>
			<div class="input-field col l4" id="divlinea">
				<select id="linea">
					<option value="0" selected>Todas</option>
   				</select>
   				<label for="linea">Linea</label>
			</div>
   			<div class="col l4 right">
   				<br>
				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="consultar">Consultar<i class="material-icons right">send</i>
	  		</button>
		</div>
		</div>
		
		<!-- INICIO DE TABLAS -->

		<div class="section"><h4 class="header center">Tablas</h4></div>

		<div class="row">
			<div class="col l6" id="tablaViajes">
			</div>
			<div class="col l6" id="tablaMontos">
			</div>
		</div>

		<!-- INICIO DE BARRAS -->

		<div class="section"><h4 class="header center">Circular</h4></div>
		
		<div class="row">
			<div class="col l6">
				<canvas id="circularPorViajes" width="400" height="400"></canvas>
			</div>
			<div class="col l6">
				<canvas id="circularPorMonto" width="400" height="400"></canvas>
			</div>
		</div>
		
		<!-- INICIO DE CIRCULARES -->
		
		<div class="section"><h4 class="header center">Barra</h4></div>
		
		<div class="row">
			<div class="col l6">
				<canvas id="barraPorViajes" width="400" height="400"></canvas>
			</div>
			<div class="col l6">
				<canvas id="barraPorMonto" width="400" height="400"></canvas>
			</div>
		</div>
		
	</div>
	
<%@ include file = "/views/footer.jsp" %>
</body>
</html>

