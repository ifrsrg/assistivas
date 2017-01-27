var valida;

$(function(){
	
	var nivel = $("#old_nivel").html();
	
	if (nivel != undefined) {
		$("#radios input[value='" + nivel + "']").prop("checked", true);
	}
	
});

function verificaNome(){
	var element = $("input").first();
	var nome = element.val();
		
	if (nome.length == 0) {
		error(element);
		message(element, "tamanho inválido");
		return false;
	}
	
	for (var i = 0; i < nome.length; i++) {
		var code = nome.toLowerCase().charCodeAt(i);
		if (!((code >= 97 && code <= 122) || (code >= 128 && code <= 136) || (code >= 160 && code <= 163) || (code == 45))) {
			error(element);
			message(element, "nome inválido");
			return false;
		}
	}
	
	$.getJSON("http://localhost:4567/selectAll", function(nomes){
		for (var i = 0; i < nomes.length; i++) {
			if (nome == nomes[i] && nome != $("#old_name").html()) {
				error(element);
				message(element, "par já existe");
				valida = false;
				return false;
			}
		}
		
		approve(element);
		retiraMessage(element);
				
		valida = true;
	
	});
		
}

function verificaNivel(){
	if ($("#radios input[type='radio']:checked").val() == undefined) {
		return false;
	}else{
		return true;
	}
}

function verifica(){
	verificaNome();
	if (valida && verificaNivel()) {
		document.getElementById("button").disabled = true;
		return true;
	}
	
	return false;
}