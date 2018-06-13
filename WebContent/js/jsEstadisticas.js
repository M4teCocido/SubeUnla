var URL_INGRESAR_FICHADA = "/SubeUnla/IngresarFichada";
var URL_ESTADISTICAS = "/SubeUnla/Estadisticas";

$(document).ready(function(){
	
	//FUNCIONES

	function updateCanvas(data){
		formatCanvas('pie', data.labels, 'Cant. de Viajes', data.viajes, $('#circularPorViajes'));
		formatCanvas('pie', data.labels, 'Montos', data.montos, $('#circularPorMonto'));
		formatCanvas('bar', data.labels, 'Cant. de Viajes', data, $('#barraPorViajes'));
		formatCanvas('bar', data.labels, 'Montos', data.montos, $('#barraPorMonto'));
	}
	
	function updateTables(data){
		for(var i = 0; i < data.labels.length; i++){
			$('#infoTable tr:last').find('tbody').append('<tr><td>' + data.labels[i] + '</td><td>' + data.viajes[i] + '</td><td>' + data.montos[i] + '</td></tr>');
		}
	}
	
	function formatCanvas(type, labels, label, data, canvas){
		var ctx = canvas;
		var background = [];
		for(var i = 0; i < labels.length; i++){
			var color = 'rgba(' + randomNumber(255) + ', ' + randomNumber(255)  + ', ' + randomNumber(255) + ', 1)';
			background.push(color);
		}
		var myChart = new Chart(ctx, {
		    type: type,
		    data: {
		        labels: labels,
		        datasets: [{
		            label: label,
		            data: data,
		            backgroundColor: background
		        }]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});
	}

	function randomNumber(max){
		return Math.floor((Math.random() * 255));
	}
	
	function setFormulario(){
		$('#desde').val('');
		$('#horaDesde').val('');
		$('#hasta').val('');
		$('#horaHasta').val('');
		$('#divlinea').hide();
	}
	
	function actualizarSelect(select, data){
		select.html('');
		select.append('<option value="0" selected>Todas</option>');
		select.append(data);
		select.formSelect();
	}
	
	//INICIALIZACION
	
	setFormulario();
	
	//AJAX
	
	$('#medio').on('change', function(){
		v = this.value;
		if(v == 1){
			$('#divlinea').show();
			var data = {
				nroValidacion : 3
			}
			
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#linea'), data);
			}).fail( function(xhr, textStatus, errorThrown) {
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
			})
		}
		if(v == 2){
			$('#divlinea').show();
			var data = {
				nroValidacion : 5
			}
				
			$.ajax({
				method: "POST",
				url: URL_INGRESAR_FICHADA,
				data: data,
				async: false
			}).done(function(data){
				actualizarSelect($('#linea'), data);
				$('#linea option:first-child').removeAttr('disabled');
			}).fail( function(xhr, textStatus, errorThrown) {
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
				$('#footerModal').modal('open');
			})
		}
		if(v == 3){
			$('#divlinea').hide();
		}
	});
	
	//AJAX CONSULTA DE ESTADISTICAS
	
	$('#consultar').click(function(){
		
		var v = $('#medio').val();
		var diaDesde = $('#desde').val().substring(0, 2);
		var mesDesde = $('#desde').val().substring(3, 5);
		var anioDesde = $('#desde').val().substring(6, 11);
		var horaDesde = $('#horaDesde').val().substring(0, 2);
		var minDesde = $('#horaDesde').val().substring(3, 5);
		var diaHasta = $('#hasta').val().substring(0, 2);
		var mesHasta = $('#hasta').val().substring(3, 5);
		var anioHasta = $('#hasta').val().substring(6, 11);
		var horaHasta = $('#horaHasta').val().substring(0, 2);
		var minHasta = $('#horaHasta').val().substring(3, 5);
		var linea = $('#linea').val();
		if (linea == null || linea == ""){
			linea = 0;
		}
		console.log("Valor Linea : " + $('#linea').val());
		if(v == 1){
			var data = {
				nroValidacion : 1,
				linea : linea,
				diaDesde : diaDesde,
				mesDesde: mesDesde,
				anioDesde : anioDesde,
				horaDesde : horaDesde,
				minDesde : minDesde,
				diaHasta : diaHasta,
				mesHasta : mesHasta,
				anioHasta : anioHasta,
				horaHasta : horaHasta,
				minHasta : minHasta
			}
			$.ajax({
				method: "POST",
				url: URL_ESTADISTICAS,
				data: data,
				async: false,
				dataType: "json"
			}).done(function(data){
				updateCanvas(data);
				updateTables(data);
			}).fail(function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				console.log("Errores : " + textStatus + " / " + errorThrown);
				
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
		
		if(v == 2){
			var data = {
				nroValidacion : 2,
				linea : linea,
				diaDesde : diaDesde,
				mesDesde: mesDesde,
				anioDesde : anioDesde,
				horaDesde : horaDesde,
				minDesde : minDesde,
				diaHasta : diaHasta,
				mesHasta : mesHasta,
				anioHasta : anioHasta,
				horaHasta : horaHasta,
				minHasta : minHasta
			}
			$.ajax({
				method: "POST",
				url: URL_ESTADISTICAS,
				data: data,
				async: false,
				dataType: "json"
			}).done(function(data){
				updateCanvas(data);
				updateTables(data);
			}).fail( function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
		
		if(v == 3){
			var data = {
				nroValidacion : 3,
				linea : linea,
				diaDesde : diaDesde,
				mesDesde: mesDesde,
				anioDesde : anioDesde,
				horaDesde : horaDesde,
				minDesde : minDesde,
				diaHasta : diaHasta,
				mesHasta : mesHasta,
				anioHasta : anioHasta,
				horaHasta : horaHasta,
				minHasta : minHasta
			}
			$.ajax({
				method: "POST",
				url: URL_ESTADISTICAS,
				data: data,
				async: false,
				dataType: "json"
			}).done(function(data){
				updateCanvas(data);
				updateTables(data);

			}).fail( function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
	});
	
});