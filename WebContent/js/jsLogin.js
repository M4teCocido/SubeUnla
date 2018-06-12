var URL_PROCESAR_LOGIN = "/SubeUnla/ProcesarLogin";

$(document).ready(function(){
	
	$('#login').click(function(){
		var data = {
			usuario : $('#usuario').val(),
			pass : $('#password').val()
		}
		$.ajax({
			method: "POST",
			url: URL_PROCESAR_LOGIN,
			data: data,
			async: false
		}).fail(function(data) {
			mostrarModal(data);
	    })
	});
	
});