var URL_LISTADOS = "/SubeUnla/Listados";

function llenarTabla(data){
	$("#tabla").html(data);
}

function limpiarTabla(){
	$("#tabla").html();
}

$(document).ready(function(){
	
	
	$('#procesar').click(function(){

		var transaccion = 1;
		//PREPARA LA DATA PARA FICHAR UNA CARGA
		var diaDesde = $('#fechaDesde').val().substring(0, 2);
		var mesDesde = $('#fechaDesde').val().substring(3, 5);
		var anioDesde = $('#fechaDesde').val().substring(6, 11);
		var horaDesde = $('#horaDesde').val().substring(0, 2);
		var minDesde = $('#horaDesde').val().substring(3, 5);
		
		var diaHasta = $('#fechaHasta').val().substring(0, 2);
		var mesHasta = $('#fechaHasta').val().substring(3, 5);
		var anioHasta = $('#fechaHasta').val().substring(6, 11);
		var horaHasta = $('#horaHasta').val().substring(0, 2);
		var minHasta = $('#horaHasta').val().substring(3, 5);
		
		if ($('#fechaDesde').val() == "" || 
				$('#horaDesde').val() == "" || 
				$("#fechaHasta").val() == "" ||
				$("#horaHasta").val() == ""){
			notificarError("Ingrese fechas y horas");
			return;
		}
		
		console.log("Fechahora Desde : " + diaDesde + " " + mesDesde + " " + anioDesde + " " + horaDesde + " " + minDesde);
		console.log("Fechahora Hasta : " + diaHasta + " " + mesHasta + " " + anioHasta + " " + horaHasta + " " + minHasta);
		
		if(transaccion == 1){
			var data = {
				nroValidacion : 1,
				diaInicial : diaDesde,
				mesInicial : mesDesde,
				anioInicial : anioDesde,
				horaInicial : horaDesde,
				minInicial : minDesde,
				diaFinal : diaHasta,
				mesFinal : mesHasta,
				anioFinal : anioHasta,
				horaFinal : horaHasta,
				minFinal : minHasta,
			}
			console.log(data);
			$.ajax({
				method: "POST",
				url: URL_LISTADOS,
				data: data,
				async: false
			}).done(function(data){
				llenarTabla(data);
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
				url: URL_LISTADOS,
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
				url: URL_LISTADOS,
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
				url: URL_LISTADOS,
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
	
})