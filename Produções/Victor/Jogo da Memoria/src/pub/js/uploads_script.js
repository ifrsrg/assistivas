$(function(){

	$.getJSON("/offsets/"+rowNum(), lista);
	
	$('#botao').on('click', function(){
		$.getJSON("/offsets/"+rowNum(), lista);
	});
	
	$('td button').on('click', function(){
		alert("hey you");
		$.postJSON("/remove/"+$(this).attr("data_id"), remove);
	});
	
	$('.remover').on('click', function(){
		alert("jasfhkjf");
		remover();
	});
	
});


function lista(vetor){
	var table = document.getElementsByTagName("table")[0];
	
	for (var i = 0; i < 5 && vetor[i] != undefined; i++) {
		table.innerHTML += "<tr><td colspan='2' class='item'>"+vetor[i].nome+" Nível: "+vetor[i].nivel+"</td></tr><tr><td><img width='320' height='240' src=/image/"+ toNameFile(vetor[i], vetor[i].form_img) + "></td>" +
				     	   "<td><video width='320' height='240' controls><source src=/video/" + toNameFile(vetor[i], vetor[i].form_vid) + " type='video/"+vetor[i].form_vid+"'>" +
				     	   "</video></td></tr><tr><td><button onclick='remove(this, " + vetor[i].data+")'>Remover</button></td></tr>";
	}
	var botao = document.getElementById("botao");
	if (vetor[5] != undefined && botao.innerHTML == "") {
		botao.innerHTML = "<button id='botao'>Carregar Mais</button>";
	} else{
		botao.innerHTML = "";
	}
}

//FAZER REMOVE

function remove(element, id){
	$(element).parent().parent().parent().hide(250);
	$.post("/remove/"+ id , function(){});

}

function rowNum(){
	return document.getElementsByClassName("item").length;
}

function toNameFile(item, formato){
	return item.id + "_" + item.nome + "_" + item.data + "." + formato;
}