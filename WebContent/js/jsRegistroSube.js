var URL_INGRESAR_FICHADA = "/SubeUnla/IngresarFichada";
var URL_ADMINISTRAR_SUBE = "/SubeUnla/AdministrarSubes";

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
		var nombreMes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
				"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
		for(var i = 1; i < 13; i++){
			$('#month').append($('<option>', {
			    value: i,
			    text: nombreMes[i - 1]
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
	
	$('#enviarRegistro').click(function(){
		var mes = $('#month').val();
		var dia = $('#day').val();
		var tipoDoc = $('#tipoDoc').val();
		var genero = $('#genero').val();
		var nombre = $('#nombre').val();
		var apellido = $('#apellido').val();
		var password = $('#password').val();
		var password2 = $('#passwordRepeat').val();
		var tarifaSocial;
		
		if (nombre.length == 0){
			notificarError("Debe ingresar su nombre");
			return;
		}
		
		if (apellido.length == 0){
			notificarError("Debe ingresar su apellido");
			return;
		}
		
		if (mes == null || dia == 0){
			notificarError("El mes ingresado es invalido!");
			return;
		}
		if (dia == null || dia == 0){
			notificarError("El dia ingresado es invalido!");
			return;
		}
		
		if (genero == null || genero == 0){
			notificarError("El genero ingresado es invalido!");
			return;
		}
		
		if (tipoDoc == null || tipoDoc == 0){
			notificarError("El Tipo de Documento ingresado es invalido!");
			return;
		}
		
		if (password.length == 0){
			notificarError("Debe ingresar su Contraseña");
			return;
		} else {
			if (password != password2){
				notificarError("La contraseñas no coinciden");
				return;				
			}
		}
		
		if (document.getElementById('tarifaSocial').checked){
			tarifaSocial = 1;
		} else {
			tarifaSocial = 0;
		}
		
		var data = {
				nroTarjeta : $('#nroTarjeta').val(),
				nroValidacion : 2,
				nombre : nombre,
				apellido : apellido,
				genero : genero,
				tipoDoc : tipoDoc,
				nroDocumento : $('#nroDocumento').val(),
				anio : $('#year').val(),
				mes : mes,
				dia : dia,
				email : $('#email').val(),
				celular : $('#celular').val(),
				telefono : $('#telefono').val(),
				descEstudiantil : $('#descEstudiantil').val(),
				password : password,
				tarifaSocial : tarifaSocial
			}
		console.log("Datos de registro enviados : " + JSON.stringify(data));
		$.ajax({
			method: "POST",
			url: URL_ADMINISTRAR_SUBE,
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