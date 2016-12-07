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
		$("body").append("<h4>Você não possui pares</h4>");
	}
	
	var table = document.getElementsByTagName("table")[0];
		
	for (var i = 0; i < 5 && vetor[i] != undefined; i++) {
		table.innerHTML += "<tr><td colspan='2' class='item'>"+vetor[i].nome+" Nível: "+vetor[i].nivel+"</td></tr><tr><td><img width='320' height='240' src=/image/" +
						   toNameFile(vetor[i], vetor[i].form_img) + "></td>" +"<td><video width='320' height='240' src = 'video/"+toNameFile(vetor[i], "ogg") +"' controls>"+
						   "</video></td></tr><tr><td><button class='remover' id_data = "+ vetor[i].data +" >Remover</button></td>" + 
				     	   "<td><a href = '/editarPar/" + vetor[i].data + "'><button>Editar</button></a></td></tr>";
	}
	var botao = document.getElementById("botao");
	if (vetor[5] != undefined && botao.innerHTML == "") {
		botao.innerHTML = "<button id='botao'>Carregar Mais</button>";
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
	return item.id + "_" + rename(item.nome) + "_" + item.data + "." + formato;
}

function rename(nome){
	var res =  nome.replace(new RegExp(" ", 'g'), "_");
	return res;
}