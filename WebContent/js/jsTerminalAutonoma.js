var URL_INGRESAR_FICHADA = "/SubeUnla/TerminalAutonoma";

$(document).ready(function(){
	
	//INICIALIZACION
	
	$('#infoModal').modal();
	
	//FUNCIONES
	
	function mostrarModal(data){
		$('#footerModal').html(data);
	    $('#footerModal').modal('open');
	}
	
	//COMPORTAMIENTO
	
	//AJAX
	
	$('#consultarTarjeta').click(function(){
		var data = {
				nroTarjeta : this.value,
				nroValidacion : 1
			}
		$.ajax({
			method: "POST",
			url: "/SubeUnla/TerminalAutonomal",
			data: data,
			async: false
		}).done(function(data){
			if(data != null && data != "" && data != ''){
				$('#nroTarjeta').val('');
				mostrarModal(data);
				$('#infoModal').modal('open');
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			$('#headerModal').html('Ups! Algo salio mal!');
			$('#pModal').html(xhr.responseText);
		    $('#footerModal').modal('open');
	    });
	})
	
});