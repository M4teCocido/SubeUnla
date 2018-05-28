$(document).ready(function(){
	var carousel = $('.carousel');
	var date = new Date();
	
	function setFormFichada(){
		$('#divlinea').hide();
		$('#divestacioninterno').hide();
		$('#divlectora').hide();
		$('#divtramo').hide();
		$('#divmonto').hide();
		$('#divestaciondestino').hide();
	}
	
	setFormFichada();
	
	$('#tipotrasaccion').on('change', function() {
		v = this.value;
		//Carga
		if(v == 1){
			$('#divlectora').show();
			//traer por ajax las lectoras disponibles
			$('#divmonto').show();
			$('#divestaciondestino').hide();
			$('#divestacioninterno').hide();
			$('#divlinea').hide();
			$('#divtramo').hide();
		}
		//Colectivo
		if(v == 2){
			$('#divlinea').show();
			$('#divestacioninterno').show();
			$('#divtramo').show();
			/*$('#estacioninterno').html('Interno');
			$('#estacioninterno').placeholder('Seleccione un interno');*/
			//ajax para cargar los interno del bondi
			//ajax para traer la lectora del interno seleccionado si es que no lo traemos directamente con el interno
			$('#divmonto').hide();
			$('#divlectora').hide();
			$('#divestaciondestino').hide();
		}
		//Subte 
		if(v == 3){
			$('#divlinea').show();
			$('#divestacioninterno').show();
			//ajax para traer las estaciones de subte de la linea
			$('#divlectora').show();
			//ajax para traer las lectoras de la estacion seleccionada
			$('#divmonto').hide();
			$('#divtramo').hide();
			$('#divestaciondestino').hide();
		}
		//Tren
		if(v == 4){
			$('#divlinea').show();
			$('#divestacioninterno').show();
			$('#divestaciondestino').show();
			$('#divlectora').show();
			$('#divmonto').hide();
			$('#divtramo').hide();
		}
	})

	$('.carousel').carousel({
		fullWidth: true,
    	indicators: true,
	});
	
	setInterval(function(){ $('.carousel').carousel('next'); }, 3500);
	
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
	
});	