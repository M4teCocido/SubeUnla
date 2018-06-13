var URL_PROCESAR_LOGIN = "/SubeUnla/ControlLogin";

$(document).ready(function(){
	
	$('#login').click(function(){
		var data = {
			nroDocumento : $('#nroDocumento').val(),
			pass : $('#password').val()
		}
		$.ajax({
			method: "POST",
			url: "/SubeUnla/ControlLogin",
			data: data,
			async: false
		}).fail(function(xhr, textStatus, errorThrown) {
			$('#headerModal').html('Ups! Algo salio mal!');
			$('#pModal').html(xhr.responseText);
		    $('#footerModal').modal('open');
	    })
	});
	
});