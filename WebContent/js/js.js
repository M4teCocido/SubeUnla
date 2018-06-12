function mostrarModal(data){
	$('#footerModal').html(data);
    $('#footerModal').modal('open');
}

function mostrarModalMensaje(titulo, msg){
	$('#headerModal').html(titulo);
	$('#pModal').html(msg);
    $('#footerModal').modal('open');
}

function notificarError(msg){
	$('#headerModal').html('Error!');
	$('#pModal').html(msg);
    $('#footerModal').modal('open');
}

function actualizarText(text, msg){
	text.val(msg);
	text.characterCounter();
}

$(document).ready(function(){
	
	//INICIALIZACIONES
	
	$('#footerModal').modal();
	
	$('.tooltipped').tooltip();

	var date = new Date();
	
	$('select').formSelect();

	$('.datepicker').datepicker({
		format: "dd-mm-yyyy",
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
		showClearBtn: true,
		twelveHour : false
	});
	
	//Metodos
	
});	