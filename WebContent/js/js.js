$(document).ready(function(){
	var carousel = $('.carousel');
	$('.carousel').carousel({
		fullWidth: true,
    	indicators: true,
	});
	
	$('select').formSelect();
	$('.datepicker').datepicker();
	$('.timepicker').timepicker();
	
	setInterval(function(){ $('.carousel').carousel('next'); }, 3500);
	
});	