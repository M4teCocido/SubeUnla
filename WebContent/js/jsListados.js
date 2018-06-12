var URL_LISTADOS = "/SubeUnla/Listados";

var TODOS = 0;
var MEDIO_TRANS_COLECTIVO = 1;
var MEDIO_TRANS_SUBTE = 2;
var MEDIO_TRANS_TREN = 3;

function llenarTabla(data){
	$("#tabla").html(data);
}

function limpiarTabla(){
	$("#tabla").html();
}

function solicitarListado(){	
	
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
	
	var medioTransporte = $("#medioTransporte").val();
	var linea = $("#linea").val();
	console.log("MedioTransporte : " + medioTransporte);
	var nroValidacion;
	
	if (medioTransporte == TODOS){
		nroValidacion = 1;
	} else if (medioTransporte == MEDIO_TRANS_COLECTIVO){
		if (linea == TODOS){
			nroValidacion = 2;
		} else {
			nroValidacion = 5;
		}
	} else if (medioTransporte == MEDIO_TRANS_SUBTE) {
		if (linea == TODOS){
			nroValidacion = 3;
		} else {
			nroValidacion = 6;
		}
	} else if (medioTransporte == MEDIO_TRANS_TREN) {
		if (linea == TODOS){
			nroValidacion = 4;
		} else {
			nroValidacion = 7;
		}
	}
	
	var data = {
			nroValidacion : nroValidacion,
			idLinea : linea,
			diaInicial : diaDesde,
			mesInicial : mesDesde,
			anioInicial : anioDesde,
			horaInicial : horaDesde,
			minInicial : minDesde,
			diaFinal : diaHasta,
			mesFinal : mesHasta,
			anioFinal : anioHasta,
			horaFinal : horaHasta,
			minFinal : minHasta
		}
		console.log("Enviando por AJAX : " + data);
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

$(document).ready(function(){
	
	$('#procesar').click(function(){
		solicitarListado();
	})
	
})