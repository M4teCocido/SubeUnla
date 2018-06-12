var URL_INGRESAR_FICHADA = "/SubeUnla/IngresarFichada";

$(document).ready(function(){

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

	setFormFichada();

	//COMPORTAMIENTO

	$('#tipotransaccion').on('change', function() {
		v = this.value;
		//console.log('Change SIN ajax - v = ' + v);
		//Carga
		if(v == 1){
			$('#divlectora').show();
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
			$('#divlectora').hide();
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
	
	$('#estacioninterno').on('change', function(){
		var transaccion = $('#tipotransaccion').val();
		if(transaccion == 3){
			$('#divlectora').show();
			$('#divmonto').hide();
		}else if(transaccion == 4){
			$('#divlectora').show();
			$('#divmonto').hide();
		}
	})

	$('#tipotransaccion').on('change', function() {

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
				$('#divmonto').show();
			}).fail( function(xhr, textStatus, errorThrown) {
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
			}).fail( function(xhr, textStatus, errorThrown) {
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
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
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
	})

	$('#numerotarjeta').change(function(){
		var data = {
				nroTarjeta : this.value,
				nroValidacion : 15
			}
		$.ajax({
			method: "POST",
			url: URL_INGRESAR_FICHADA,
			data: data,
			async: false
		}).done(function(data){
			if(data != null && data != "" && data != ''){
				mostrarModal(data);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			$('#headerModal').html('Ups! Algo salio mal!');
			$('#pModal').html(xhr.responseText);
		    $('#footerModal').modal('open');
	    });
	})

	$('#enviarfichada').click(function(){

		var transaccion = $('#tipotransaccion').val();
		//PREPARA LA DATA PARA FICHAR UNA CARGA
		var dia = $('#fecha').val().substring(0, 2);
		var mes = $('#fecha').val().substring(3, 5);
		var anio = $('#fecha').val().substring(6, 11);
		var hora = $('#hora').val().substring(0, 2);
		var min = $('#hora').val().substring(3, 5);
		if ($('#fecha').val() == "" || $('#hora').val() == ""){
			notificarError("Ingrese fecha y hora");
			return;
		}
		console.log("Fecha : " + $('#fecha').val());
		console.log("Fechahora : " + dia + " " + mes + " " + anio + " " + hora + " " + min);
		
		if(transaccion == 1){
			var nroTarjeta = $('#numerotarjeta').val();
			var idLectora = $('#lectora').val();
			var monto = $('#monto').val();
			if (monto == null || monto == ""){
				notificarError("El monto ingresado es invalido!");
				return;
			}
			if (idLectora == null){
				notificarError("La lectora seleccionada es invalida!");
				return;
			}
			var data = {
				nroTarjeta : nroTarjeta,
				nroValidacion : 11,
				dia : dia,
				mes : mes,
				anio : anio,
				hora : hora,
				min : min,
				idLectora : idLectora,
				monto : monto
			}
			console.log(data);
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				mostrarModal(data);
			}).fail( function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
		
		//PREPARA LA DATA PARA PROCESAR FICHADA COLECTIVO
		if(transaccion == 2){
			var nroTarjeta = $('#numerotarjeta').val();
			var idLinea = $('#linea').val();
			var idInterno = $('#estacioninterno').val();
			var idTramo = $('#tramo').val();
			
			if (idLinea == null){
				notificarError("La Linea seleccionada es invalida!");
				return;
			} else if (idTramo == null){
				notificarError("El Tramo seleccionado es invalido!");
				return;
			} else if (idInterno == null){
				notificarError("El Interno seleccionado es invalido!");
				return;
			} 
			
			var data = {
				nroTarjeta : nroTarjeta,
				nroValidacion : 12,
				dia : dia,
				mes : mes,
				anio : anio,
				hora : hora,
				min : min,
				idLinea : idLinea,
				idInterno : idInterno,
				idTramo : idTramo
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				mostrarModal(data);
			}).fail( function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
		//PREPARA LA DATA PARA PROCESAR FICHADA SUBTE
		if(transaccion == 3){
			var nroTarjeta = $('#numerotarjeta').val();
			var idLinea = $('#linea').val();
			var idEstacion = $('#estacioninterno').val();
			var idLectora = $('#lectora').val();
			
			if (idLinea == null){
				notificarError("La Linea seleccionada es invalida!");
				return;
			} else if (idEstacion == null){
				notificarError("La Estacion seleccionada es invalida!");
				return;
			} else if (idLectora == null){
				notificarError("La Lectora seleccionada es invalida!");
				return;
			}
			var data = {
				nroTarjeta : nroTarjeta,
				nroValidacion : 13,
				dia : dia,
				mes : mes,
				anio : anio,
				hora : hora,
				min : min,
				idLinea : idLinea,
				idEstacion : idEstacion,
				idLectora : idLectora
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				mostrarModal(data);
			}).fail( function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
		//PREPARA LA DATA PARA PROCESAR FICHADA TREN
		if(transaccion == 4){
			var nroTarjeta = $('#numerotarjeta').val();
			var idLinea = $('#linea').val();
			var idEstacion = $('#estacioninterno').val();
			var idLectora = $('#lectora').val();
			
			if (idLinea == null){
				notificarError("La Linea seleccionada es invalida!");
				return;
			} else if (idEstacion == null){
				notificarError("La Estacion seleccionada es invalida!");
				return;
			} else if (idLectora == null){
				notificarError("La Lectora seleccionada es invalida!");
				return;
			}
			
			var data = {
				nroTarjeta : nroTarjeta,
				nroValidacion : 14,
				dia : dia,
				mes : mes,
				anio : anio,
				hora : hora,
				min : min,
				idLinea : idLinea,
				idEstacion : idEstacion,
				idLectora : idLectora
			}
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				mostrarModal(data);
			}).fail( function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
	})

});