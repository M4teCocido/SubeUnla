$(document).ready(function(){
	var carousel = $('.carousel');
	var date = new Date();
	
	$('.carousel').carousel({
		fullWidth: true,
    	indicators: true,
	});
	
	$('select').formSelect();
	$('.datepicker').datepicker({
		format: "d-m-yyyy",
		maxDate: date,
		yearRange: 2,
		showClearBtn: true
	});
	$('.timepicker').timepicker({
		showClearBtn: true
	});

	setInterval(function(){ $('.carousel').carousel('next'); }, 3500);
	
});	