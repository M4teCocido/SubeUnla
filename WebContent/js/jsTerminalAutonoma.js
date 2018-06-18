var URL_TERMINAL = "/SubeUnla/AdministrarSubes";

$(document).ready(function(){
	
	//INICIALIZACION
	
	//FUNCIONES
	
	function mostrarModal(data){
		$('#headerModal').html('<h3>Informacion General:</h3>');
		$('.modal-content').append(data)
	    $('#footerModal').modal('open');
	}
	
	//COMPORTAMIENTO
	
	//AJAX
	
	$('#consultarTarjeta').click(function(){
		var data = {
			nroTarjeta : $('#nroTarjeta').val(),
			nroValidacion : 11
		}
		$.ajax({
			method: "POST",
			url: URL_TERMINAL,
			data: data,
			async: false
		}).done(function(data){
			console.log(data);
			if(data != null && data != "" && data != ''){
				$('#nroTarjeta').val('');
				$('').append();
				mostrarModal(data);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			$('#headerModal').html('Ups! Algo salio mal!');
			$('#pModal').html(xhr.responseText);
		    $('#footerModal').modal('open');
	    });
	})
	
});