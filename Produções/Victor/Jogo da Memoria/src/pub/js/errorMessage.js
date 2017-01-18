function message(element, message){
	
	var erro = $(element).next();
	
	if (erro.hasClass("erro")) {
		erro.html(message);
	}else{
		$('<span class="help-block erro text-left">'+message+'</span>').insertAfter($(element));
		$(element).next().show('slow');
	}
}

function retiraMessage(element){
	var erro = $(element).next();
	if (erro.hasClass("erro")) {
		erro.hide(500, function(){
			erro.remove();
		});	
	}
}