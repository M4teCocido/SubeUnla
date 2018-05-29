$(document).ready(function(){
	//INICIALIZACIONES
	
	$('input, #input_text, textarea#textarea2').characterCounter();
	
	$('.carousel').carousel({
		fullWidth: true,
    	indicators: true,
	});
	
	var carousel = $('.carousel');
	setInterval(function(){ $('.carousel').carousel('next'); }, 3500);

	var date = new Date();	
	
	$('select').formSelect();
	
	$('.datepicker').datepicker({
		format: "d-m-yyyy",
		maxDate: date,
		yearRange: 2,
		showClearBtn: true,
		i18n: {
			months: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			monthsShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
			weekdays: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
			weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb']
		}
	});
	
	$('.timepicker').timepicker({
		showClearBtn: true
	});
	
	setFormFichada();
	
	//FUNCIONES
	
	function setFormFichada(){
		$('#divlinea').hide();
		$('#divestacioninterno').hide();
		$('#divlectora').hide();
		$('#divtramo').hide();
		$('#divmonto').hide();
	}
	
	//COMPORTAMIENTO
	
	$('#tipotrasaccion').on('change', function() {
		v = this.value;
		//Carga
		if(v == 1){
			$('#divlectora').show();
			//traer por ajax las lectoras disponibles
			$('#divmonto').show();
			$('#divestacioninterno').hide();
			$('#divlinea').hide();
			$('#divtramo').hide();
		}
		//Colectivo
		if(v == 2){
			$('#divlinea').show();
			$('#divestacioninterno').hide();
			$('#divtramo').hide();
			/*$('#estacioninterno').html('Interno');
			$('#estacioninterno').placeholder('Seleccione un interno');*/
			//ajax para cargar los interno del bondi
			//ajax para traer la lectora del interno seleccionado si es que no lo traemos directamente con el interno
			$('#divmonto').hide();
			$('#divlectora').hide();
		}
		//Subte 
		if(v == 3){
			$('#divlinea').show();
			$('#divestacioninterno').hide();
			//ajax para traer las estaciones de subte de la linea
			$('#divlectora').hide();
			//ajax para traer las lectoras de la estacion seleccionada
			$('#divmonto').hide();
			$('#divtramo').hide();
		}
		//Tren
		if(v == 4){
			$('#divlinea').show();
			$('#divestacioninterno').hide();
			$('#divlectora').hide();
			$('#divmonto').hide();
			$('#divtramo').hide();
		}
	});

	//AJAX
	
	/*$('#numerotarjeta').focusout(function(){
		var data = {
			nroValidacion: 1,
			nroTarjeta : this.value
		}
		$.ajax({
			method: "POST",
			url: "ControladorIngresarFichada",
			data: { data: data },
			async: false
		}).done(function(data){
			
		})
	});*/
	
	$('#tipotrasaccion').on('change', function() {
		v = this.value;
		if(v == 1){
			var data = {
				nroValidacion = 2
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#lectora').html(data);
				$('#lectora').show();
				$('#monto').show();
			})
		}
		if(v == 2){
			var data = {
				nroValidacion = 3
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#linea').html(data);
				$('#linea').show();
			})
		}
		if(v == 3){
			var data = {
				nroValidacion = 4
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#linea').html(data);
				$('#linea').show();
			})
		}
		if(v == 4){
			var data = {
				nroValidacion : 5
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#linea').html(data);
				$('#linea').show();
			})
		}
	})
	
	$('#linea').on('change', function(){
		var transaccion = $('#tipotrasaccion').value();
		
		//PREPARA LA INFO PARA SOLICITAR LOS INTERNOS DE LA LINEA DE COLECTIVO SELECCIONADA Y HACE LA PETICION
		if(transaccion == 2){
			var data = {
				nroValidacion : 6,
				idLinea : this.value
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#estacioninterno').html(data);
				$('#estacioninterno').show();
			})
			var data = {
				nroValidacion : 1,
				idLinea : this.value
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#tramo').html(data);
				$('#tramo').show();
			})
		}
		//PREPARA LA INFO PARA SOLICITAR LAS ESTACIONES DE LA LINEA DE SUBTE SELECCIONADA Y HACE LA PETICION
		if(transaccion == 3){
			var data = {
				nroValidacion : 7,
				idLinea : this.value
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#estacioninterno').html(data);
				$('#estacioninterno').show();
			})
		}
		//PREPARA LA INFO PARA SOLICITAR LAS ESTACIONES DE LA LINEA DE TREN SELECCIONADA Y HACE LA PETICION
		if(transaccion == 4){
			var data = {
				nroValidacion : 8,
				idLinea : this.value
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#estacioninterno').html(data);
				$('#estacioninterno').show();
			})
		}
	})
	
	$('#estacioninterno').on('change', function(){
		var transaccion = $('#tipotrasaccion').value();
		if(transaccion == 3){
			var data = {
				nroValidacion : 9,
				idLinea : $('#linea').value(),
				idEstacion : this.value
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#lectora').html(data);
				$('#lectora').show();
			})
		}
		if(transaccion == 4){
			var data = {
				nroValidacion : 10,
				idLinea : $('#linea').value(),
				idEstacion : this.value
			}
			$.ajax({
				method: "POST",
				url: "ControladorIngresarFichada",
				data: { data: data },
				async: false
			}).done(function(data){
				$('#lectora').html(data);
				$('#lectora').show();
			})
		}
	})
	
});	