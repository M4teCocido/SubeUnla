var URL_INGRESAR_FICHADA = "/SubeUnla/IngresarFichada";
var URL_ESTADISTICAS = "/SubeUnla/Estadisticas";


var activeCharts = [null, null, null, null];

$(document).ready(function(){
	
	//FUNCIONES
		
	function updateCanvas(data){
		var labels = data.labels;
		var viajes = data.viajes;
		var montos = data.montos;
		
		formatCanvas('pie', labels, 'Cant. de Viajes', viajes, $('#circularPorViajes'), 0);
		formatCanvas('pie', labels, 'Montos', montos, $('#circularPorMonto') , 1);
		formatCanvas('bar', labels, 'Cant. de Viajes', viajes, $('#barraPorViajes'), 2);
		formatCanvas('bar', labels, 'Montos', montos, $('#barraPorMonto'), 3);
	}
	
	function updateTables(data){
		$('#infoTable').html('');
		for(var i = 0; i < data.labels.length; i++){
			$('#infoTable').append('<tr><td>' + data.labels[i] + '</td><td>' + data.viajes[i] + '</td><td>' + data.montos[i] + '</td></tr>');
		}
	}
	
	function formatCanvas(type, labels, label, data, canvas, chartIndex){
		var ctx = canvas;
		//ctx.html('');
		console.log("Canvas : ");
		console.log(canvas);
		
		var rawCanvas = canvas[0];
		const context = rawCanvas.getContext('2d');
		context.clearRect(0, 0, rawCanvas.width, rawCanvas.height);
		console.log("ctx boundaries : [" + rawCanvas.width + "," + rawCanvas.height + "]");

		var background = [];
		for(var i = 0; i < labels.length; i++){
			var color = 'rgba(' + randomNumber(255) + ', ' + randomNumber(255)  + ', ' + randomNumber(255) + ', 1)';
			background.push(color);
		}
		
		if (activeCharts[chartIndex] != null){
			activeCharts[chartIndex].destroy();
		}
		
		activeCharts[chartIndex] = new Chart(ctx, {
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
			$('#divlinea').hide();
		}
		if(v == 3){
			$('#divlinea').hide();
			/*
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
			})*/
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
		//SOLICITA INFORMACION ESTADISTICA DEL MEDIO DE TRANSPORTE COLECTIVO
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
				console.log(data);
				updateTables(data);
				updateCanvas(data);
				
				
			}).fail(function(xhr, textStatus, errorThrown) {
				//alert("Error al devolver AJAX. Mensaje : " + xhr.responseText);
				console.log("Errores : " + textStatus + " / " + errorThrown);
				$('#headerModal').html('Ups! Algo salio mal!');
				$('#pModal').html(xhr.responseText);
			    $('#footerModal').modal('open');
		    })
		}
		//SOLICITA INFORMACION ESTADISTICA DEL MEDIO DE TRANSPORTE SUBTE
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
		//SOLICITA INFORMACION ESTADISTICA DEL MEDIO DE TRANSPORTE TREN
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
				console.log(data);
				console.log(data.viajes);
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