$(document).ready(function(){
	var nivel = $("select").attr("value");
	
	$('select option[value='+nivel+']').attr('selected','selected');
	
});