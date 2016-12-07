var valida;

function verificaNome(){
	var element = $("input").first();
	var nome = element.val();
	
	for (var i = 0; i < nome.length; i++) {
		var code = nome.toLowerCase().charCodeAt(i);
		if (!(code >= 97 && code <= 122)) {
			message(element, "nome inválido");
			return false;
		}
	}
	
	$.getJSON("http://localhost:4567/selectAll", function(nomes){
		for (var i = 0; i < nomes.length; i++) {
			if (nome == nomes[i]) {
				message(element, "par já existe");
				valida = false;
				return false;
			}
		}
		retiraMessage(element);
		valida = true;
	
	});
		
}

function verifica(){
	verificaNome();
	if (valida) {
		document.getElementById("button").disabled = true;
	}
	
	return valida;
}