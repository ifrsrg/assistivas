var valida;

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
		if (!(code >= 97 && code <= 122)) {
			error(element);
			message(element, "nome inválido");
			return false;
		}
	}
	
	$.getJSON("http://localhost:4567/selectAll", function(nomes){
		for (var i = 0; i < nomes.length; i++) {
			if (nome == nomes[i]) {
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