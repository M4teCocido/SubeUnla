var URL_PROCESAR_LOGIN = "/SubeUnla/ProcesarLogin";

$(document).ready(function(){
	
	$('#login').click(function(){
		var data = {
			nroDocumento : $('#nroDocumento').val(),
			pass : $('#password').val()
		}
		$.ajax({
			method: "POST",
			url: URL_PROCESAR_LOGIN,
			data: data,
			async: false
		}).fail(function(data) {
			$('#headerModal').html('Ups! Algo salio mal!');
			$('#pModal').html(xhr.responseText);
		    $('#footerModal').modal('open');
	    })
	});
	
});