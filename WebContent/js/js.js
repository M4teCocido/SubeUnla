var URL_INGRESAR_FICHADA = "/SubeUnla/IngresarFichada";

$(document).ready(function(){

	//FUNCIONES
	
	function setFormFichada(){
		$('#divlinea').hide();
		$('#divestacioninterno').hide();
		$('#divlectora').hide();
		$('#divtramo').hide();
		$('#divmonto').hide();
	}
	
	//INICIALIZACIONES
	
	$('.modal').modal();
	var modal = M.Modal.getInstance($('#footerModal'));
	
	$('.carousel').carousel({
		fullWidth: true,
    	indicators: true
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
			weekdays: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
			weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab']
		}
	});
	
	$('input, #input_text, textarea#textarea2').characterCounter();

	$('.timepicker').timepicker({
		showClearBtn: true
	});
	
	setFormFichada();
	
	$('#tipotransaccion').change(function() {
	//FUNCIONES
	
	function actualizarSelect(select, data){
		select.html(data);
		select.formSelect();
	}
	
	function setFormFichada(){
		$('#divlinea').hide();
		$('#divestacioninterno').hide();
		$('#divlectora').hide();
		$('#divtramo').hide();
		$('#divmonto').hide();
	}
	
	//COMPORTAMIENTO
	
	$('#tipotransaccion').on('change', function() {
		v = this.value;
		//console.log('Change SIN ajax - v = ' + v);
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
	
	//COMPORTAMIENTO
	
	$('#linea').on('change', function(){
		var transaccion = $('#tipotransaccion').val();
		console.log("Linea.Change() : Transaccion = " + transaccion);
		if (transaccion == 2){ //Colectivo
			$('#divtramo').show();
			$("#divestacioninterno").show();
		} else if (transaccion == 3) { //Subte 
			$("#divestacioninterno").show();
		} else if (transaccion == 4){ //Tren
			$("#divestacioninterno").show();
		}
	})
	
	//AJAX
	
	/*$('#numerotarjeta').focusout(function(){
		var data = {
			nroValidacion: 1,
			nroTarjeta : this.value
		}
		$.ajax({
			method: "POST",
			url: URL_INGRESAR_FICHADA,
			data: data,
			async: false
		}).done(function(data){
			
		})
	});*/
	
	/*$('#tipotrasaccion').on('change', function() {
=======
	$('#tipotransaccion').on('change', function() {
>>>>>>> 66f782baca441aa5bbf464f14e2031a8001e79c6
		v = this.value;
		//console.log('Change CON ajax - v = ' + v);
		if(v == 1){
			var data = {
				nroValidacion : 2
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#lectora'), data);
				$('#monto').show();
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
		} else if (v == 2){
			var data = {
				nroValidacion : 3
			}
			
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#linea'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })

		} else if(v == 3){
			var data = {
				nroValidacion : 4
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: true
			}).done(function(data){
				actualizarSelect($('#linea'), data);
				//$('#linea').show();
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
			
		} else if(v == 4){
			var data = {
				nroValidacion : 5
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#linea'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
		}
	})
	
	$('#linea').on('change', function(){
		var transaccion = $('#tipotransaccion').val();
		
		//PREPARA LA INFO PARA SOLICITAR LOS INTERNOS DE LA LINEA DE COLECTIVO SELECCIONADA 
		//Y HACE LA PETICION
		if(transaccion == 2){
			var data = {
				nroValidacion : 6,
				idLinea : this.value
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#estacioninterno'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
			
			var data = {
				nroValidacion : 1,
				idLinea : this.value
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#tramo'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
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
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#estacioninterno'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
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
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#estacioninterno'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
		}
	})
	
	$('#estacioninterno').on('change', function(){
		var transaccion = $('#tipotransaccion').val();
		if(transaccion == 3){
			var data = {
				nroValidacion : 9,
				idLinea : $('#linea').val(),
				idEstacion : this.value
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#lectora'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
		}
		if(transaccion == 4){
			var data = {
				nroValidacion : 10,
				idLinea : $('#linea').val(),
				idEstacion : this.value
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#lectora'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
		    })
		}
	})
	*/
	
});	