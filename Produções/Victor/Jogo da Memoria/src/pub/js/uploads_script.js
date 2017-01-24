$(function(){

	$.getJSON("/offsets/"+rowNum(), lista);
	
	$('#botao').on('click', function(){
		$.getJSON("/offsets/"+rowNum(), lista);
	});
	
	$(document).on('click', '.remover', function(){
		remove(this);
	});
});


function lista(vetor){
	
	if (vetor.length == 0) {
		$("#conteudo").append("<h4 class='text-center'>Você não possui pares</h4>");
	}
	
	var table = document.getElementById("conteudo");
		
	for (var i = 0; i < 5 && vetor[i] != undefined; i++) {
		table.innerHTML += "<div class='row item'><div class='col-sm-12'><div class='row'><div class='col-sm-9'>" + 
						   "<span class='nome'>" + vetor[i].nome + "</span></div><div class='col-sm-3'><span class='nivel'>Nível: " + vetor[i].nivel + "</span></div></div><div class='row'>" +
						   "<img class='col-sm-6 media-object' src='/image/" + toNameFile(vetor[i], vetor[i].form_img) + "'>" +
						   "<video class='col-sm-6 media-object' src='/video/"+toNameFile(vetor[i], 'ogg') + "' controls muted></video>" +
						   "</div><br><div class='form-group row'><div class='col-sm-6'><button id_data = "+ vetor[i].data +
						   " class='btn btn-default form-control remover'>Remover</button></div><div class='col-sm-6'>" +
						   "<a href = '/editarPar/" + vetor[i].data + "' class='btn btn-default form-control '>Editar</a></div></div></div></div>";
	}
	var botao = document.getElementById("botao");
	if (vetor[5] != undefined && botao.innerHTML == "") {
		botao.innerHTML = "<button id='botao' class='form-control'>Carregar Mais</button>";
	} else{
		botao.innerHTML = "";
	}
}

function remove(element){
	$(element).parent().parent().parent().hide(250);
	$.post("/remove/"+ $(element).attr("id_data") , function(){});
}

function rowNum(){
	return document.getElementsByClassName("item").length;
}

function toNameFile(item, formato){
	return item.id + "_" + rename(item.nome) + "_" + item.data + "." + formato +"?v="+ new Date().getTime();
}

function rename(nome){
	var res =  nome.replace(new RegExp(" ", 'g'), "_");
	return res;
}