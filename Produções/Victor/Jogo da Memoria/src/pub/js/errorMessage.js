function message(element, message){
	var erro = $(element).parent().parent();
	if (!erro.prev().hasClass("erro")) {
		$("<tr class='erro'><td>" + message + "</td></tr>").insertBefore(erro);
		$(".erro").show(500);
	}
}

function retiraMessage(element){
	var erro = $(element).parent().parent().prev();
	if (erro.hasClass("erro")) {
		erro.hide(500, function(){
			erro.remove();
		});	
	}
}