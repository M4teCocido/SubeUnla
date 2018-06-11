$(document).ready(function(){
		
	//INICIALIZACION
	
	setFormRegistro();
	
	//FUNCIONES
	
	function setFormRegistro(){
		$('#nroTarjeta').val('');
		$('#nombre').val('');
		$('#apellido').val('');
		$('#tipoDoc').val('');
		$('#nroDocumento').val('');
		setMonthSelect();
		setYearSelect();
		$('#day').val('');
		$('#email').val('');
		$('#celular').val('');
		$('#telefono').val('');
		$('#descTarifaSocial').prop('checked', false);
		$('#descEstudiantil').prop('checked', false);
	}

	function setYearSelect(){
		var actualYear = new Date().getFullYear();
		for(var i = actualYear - 80; i < actualYear; i++){
			$('#year').append($('<option>', {
				value : i,
				text : i
			}));
		}
		$("#year option:last").attr("selected", "selected");
		$('#year').formSelect();
	}
	
	function setMonthSelect(){
		for(var i = 1; i < 13; i++){
			$('#month').append($('<option>', {
			    value: i,
			    text: i
			}));
		}
		$('#month').formSelect();
	}
	
	function setDaysSelect(days){
		$('#day').empty();
		for(var i = 1; i <= days; i++){
			$('#day').append($('<option>', {
				value : i,
				text : i
			}));
		}
		$('#day').formSelect();
	}
	
	$('#month').on('change', function(){
		var year = $('#year').val();
		var month = $('#month').val();
		console.log(month);
		var days = 0;
		var isLeapYear = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? true : false;
		switch (month) {
		case "4":
		case "6":
		case "9":
		case "11":
			days = 30;
			break;
	    case "2":
	        days = (isLeapYear) ? 29 : 28;
	        break;
	    default:
	        days = 31;
		}
		console.log(days);
		setDaysSelect(days);
	});
	
	//COMPORTAMIENTO
	
	$('#limpiarForm').click(function(){
		setFormRegistro();
	});
	
	//AJAX


});