var URL_ADMINISTRAR_SUBE = "/SubeUnla/AdministrarSubes";

$(document).ready(function(){
	
	$('#bajaTarjeta').click(function(){
		var data = {
			nroTarjeta : $('#bajaTarjeta').prop('codigo'),
	        nroValidacion : 3
	    	}
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
	    		mostrarModalMensaje("Error!", "Alguno de los campos es invalido!");
    	});
	});
	
});