var valida_login = true;

function verificaNome(element){
	var nome = element.value;
	
	if (nome.length < 4 || nome.length > 60) {
		error(element);
		message(element, "Tamanho inválido");
		return false;
	}
	
	for (var i = 0; i < nome.length; i++) {
		var code = nome.toLowerCase().charCodeAt(i);
		if (!((code >= 97 && code <= 122) || (code >= 222 && code <= 256) || (code == 32))) {
			error(element);
			message(element, "nome inválido");
			return false;
		}
	}
	
	retiraMessage(element);
	
	approve(element);
	
	return true;
}

function verificaLogin(element){
	valida_login = true;
	var login = element.value;
	
	if (login.length > 15 || login.length < 4) {
		error(element);
		message(element, "login inválido");
		valida_login = false;
		return false;
	}
	
	for (var i = 0; i < login.length; i++) {
		var code = login.toLowerCase().charCodeAt(i);
		if (!((code >= 97 && code <= 122) || (code >= 48 && code <= 57))) {
			error(element);
			message(element, "login inválido");
			valida_login = false;
			return false;
		}
	}
	
	$.getJSON("/users/"+login, function(item){
		if (item == login) {
			error(element);
			message(element, "login existe");
			valida_login = false;
			
		}
		
		if (valida_login == false) {
			return false;
		}
		
		retiraMessage(element);
		
		approve(element);
		return true;

	});
	
	
}

function Ajax(login){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	var response = JSON.parse(xhttp.responseText);
	    	Valida(response, login);
	    }
	  };
	  xhttp.open("GET", "/users/"+login, true);
	  xhttp.send();
}

function Valida(ajax, login){
	if (ajax == login) {
		var element = $("#login");
		error(element);
		message(element, "login existe");
		valida_login = false;
		alert(1);
		
	}
}

function verificaSenha(element){
	var senha = element.value;
	var login = document.getElementsByName('login')[0].value;
	var nome = document.getElementsByName('nome')[0].value;
		
	if (senha === login || senha === nome) {
		error(element);
		message(element, "Senha não pode ser igual ao nome ou login");
		return false;
	}
	
	if(senha.length < 5 || senha.length > 20){
		error(element);
		message(element, "Tamanho de senha inválida");
		return false;
	}
	
	for (var i = 0; i < senha.length; i++) {
		var code = senha.toLowerCase().charCodeAt(i);
		if (!((code >= 97 && code <= 122) || (code >= 48 && code <= 57))) {
			error(element);
			message(element, "senha inválida");
			return false;
		}
	}
	
	retiraMessage(element);
	approve(element);
	
	return true;
}

function verificaSenha2(element){
	var senha_element = element.value;
	var senha1 = document.getElementsByName('senha')[0].value;
	if (senha_element != senha1) {
		error(element);
		message(element, "Senhas diferentes");
		return false;
	}
	return true;
}

function verificaSubmit(){
	var nome = document.getElementsByName('nome')[0];
	var login = document.getElementsByName('login')[0];
	var senha = document.getElementsByName('senha')[0];
	var senha2 = document.getElementsByName('senha2')[0];
	
	if (verificaNome(nome) && valida_login
		&& verificaSenha(senha) && verificaSenha2(senha2)){
		document.getElementById("button").disabled = true;
		return true;
	}
	return false;
}