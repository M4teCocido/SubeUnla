//var URL_REGISTRO_TARJETA = "/SubeUnla/RegistroTarjeta";

$(document).ready(function(){
		
	//INICIALIZACION
	
	setFormRegistro();
	
	//FUNCIONES
	
	function notificarError(msg){
		$('#headerModal').html('Error!');
		$('#pModal').html(msg);
	    $('#footerModal').modal('open');
	}
	
	function setFormRegistro(){
		$('#nroTarjeta').val('');
		$('#nombre').val('');
		$('#apellido').val('');
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
	
	$('#passwordRepeat').focusout(function(){
		var pass = $('#password').val();
		var passRepeat = this.value;
		if(pass != passRepeat) notificarError("Las contraseñas no coinciden");
	});
	
	//AJAX

	$('#nroTarjeta').focusout(function(){
		var data = {
				nroTarjeta : this.value,
				nroValidacion : 1
			}
		$.ajax({
			method: "POST",
			url: "/SubeUnla/RegistroTarjeta",
			data: data,
			async: false
		}).fail(function(data) {
			if(data != null && data != "" && data != ''){
				$('#nroTarjeta').val('');
				mostrarModal(data);
			}
	    });
	})
	
	$('#enviarRegistro').click(function(){
		var data = {
				nroTarjeta : $('#nroTarjeta').val(),
				nroValidacion : 2,
				nombre : $('#nombre').val(),
				apellido : $('#apellido').val(),
				genero : $('#genero').val(),
				tipoDoc : $('#tipoDoc').val(),
				nroDocumento : $('#nroDocumento').val(),
				year : $('#year').val(),
				month : $('#month').val(),
				day : $('#day').val(),
				email : $('#email').val(),
				celular : $('#celular').val(),
				telefono : $('#telefono').val(),
				descEstudiantil : $('#descEstudiantil').val(),
				passwords : $('#password').val(),
				tarifaSocial : $('#tarifaSocial').val()
			}
		$.ajax({
			method: "POST",
			url: "/SubeUnla/RegistroTarjeta",
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
	
});