function Ajax(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	alert(xhttp.responseText);
	    	var vetor = JSON.parse(xhttp.responseText);
	    	var table = document.getElementsByTagName("table")[0];
	    	for (var i = 0; i < 5 && vetor[i] != undefined; i++) {
				table.innerHTML += "<tr><td colspan='2' class='item'>"+vetor[i].nome+" Nível: "+vetor[i].nivel+"</td></tr><tr><td><img width='320' height='240' src=/image/"+ toNameFile(vetor[i], vetor[i].form_img) + "></td>" +
						     	   "<td><video width='320' height='240' controls><source src=/video/" + toNameFile(vetor[i], vetor[i].form_vid) + " type='video/"+vetor[i].form_vid+"'>" +
						     	   "</video></td></tr>";
			}
    		var botao = document.getElementById("botao");
	    	if (vetor[5] != undefined && botao.innerHTML == "") {
				botao.innerHTML = "<button onclick='Ajax()'>Carregar Mais</button>";
			} else{
				botao.innerHTML = "";
			}
	    	
	    }
	 };
	 xhttp.open("GET", "/offsets/"+rowNum(), true);
	 xhttp.send();
}

function rowNum(){
	alert(document.getElementsByClassName("item").length);
	return document.getElementsByClassName("item").length;
}

function toNameFile(item, formato){
	return item.id + "_" + item.nome + "_" + item.data + "." + formato;
}