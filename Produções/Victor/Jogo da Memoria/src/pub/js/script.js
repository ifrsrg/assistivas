var valida_login = true;

function verificaNome(element){
	var nome = element.value;
	
	if (nome.length < 4 || nome.length > 60) {
		alert("Tamanho inválido");
		return false;
	}
	
	for (var i = 0; i < nome.length; i++) {
		var code = nome.toLowerCase().charCodeAt(i);
		if (!((code >= 97 && code <= 122) || (code >= 222 && code <= 256) || (code == 32))) {
			alert("nome inválido");
			return false;
		}
	}
	
	return true;
}

function verificaLogin(element){
	valida_login = true;
	var login = element.value;
	
	if (login.length > 15 || login.length < 4) {
		alert("login inválido");
		valida_login = false;
		return false;
	}
	
	Ajax(login);
	
	if (valida_login == false) {
		return false;
	}
	
	return true;
}

function Ajax(login){
	alert(login);
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
	alert(ajax);
	alert(login);
	if (ajax === login) {
		alert("login existe");
		valida_login = false;
	}
}

function verificaSenha(element){
	var senha = element.value;
	var login = document.getElementsByName('login')[0].value;
	var nome = document.getElementsByName('nome')[0].value;
		
	if (senha === login || senha === nome) {
		alert("senha não pode ser igual ao nome ou login");
		return false;
	}
	
	if(senha.length < 5 || senha.length > 20){
		alert("tamanho de senha inválida");
		return false;
	}
	
	return true;
}

function verificaSenha2(element){
	var senha_element = element.value;
	var senha1 = document.getElementsByName('senha')[0].value;
	if (senha_element != senha1) {
		alert("senhas diferentes");
		return false;
	}
	
	return true;
}

function verificaSubmit(){
	var nome = document.getElementsByName('nome')[0];
	var login = document.getElementsByName('login')[0];
	var senha = document.getElementsByName('senha')[0];
	var senha2 = document.getElementsByName('senha2')[0];
	
	alert("Nome " + verificaNome(nome));
	alert("Login " + valida_login);
	alert("verificaSenha(senha) " + verificaSenha(senha));
	alert("verificaSenha2(senha2) " + verificaSenha2(senha2));
	
	if (verificaNome(nome) && valida_login
		&& verificaSenha(senha) && verificaSenha2(senha2)){
		return true;
	}
	return false;
}