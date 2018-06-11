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
	
});	