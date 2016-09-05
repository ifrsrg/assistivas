var tabuleiro = [];
var pares = [];

function carregarJogo(){
	var nivel = getNivel();
	var tamanho = getTamanho();
	var tipo_pares = getTipoPares();
	Ajax(nivel, tamanho, tipo_pares);
	montar(nivel, tamanho);
}

function montar(nivel, tamanho){ //monta o HTML através do innerHTML
    var linhas, tamanho, colunas,change = "";
    var elemento = document.getElementsByTagName("table")[0];
    alert(tamanho);
    switch(tamanho){
        case "6":  linhas = 2;
                 colunas = 6; break;
        case "10": linhas = 3;
                 colunas = 8; break;
        case "12": linhas = 3;
                 colunas = 10;
    }    
    
    alert(linhas + "   " + colunas);
    
    var count = 0;
    
    for (var i = 0; i < linhas; i++){
        change += "<tr>";
        for (var j = 0; j < colunas; j++){
            change += "<td><img class='carta' onclick='joga(" + count + ")' src='image/cover.jpg' id='"+count+"'></td>";
            count++;
        }
        change += "</tr>";
    }
    alert("Hey");
    elemento.innerHTML = change + "</table>";
}

function embaralha(vetor){
    var vezes = 0;
    pares = JSON.parse(vetor);
    var tamanho = pares.length;
    alert(tamanho);
    for (var i = 0; i < tamanho*2;){
        var aleatorio = parseInt((Math.random()*(tamanho)));
        //verifica se existem dois pares
        for (var j = 0; j < tabuleiro.length; j++)
            if (vezes < 2 && pares[tabuleiro[j]] === pares[aleatorio]) vezes++;
        //add no tabuleiro
        if (vezes < 2){
            tabuleiro[i] = aleatorio;
            i++;
        }
        vezes = 0;
    }
    alert(tabuleiro);
}

function Ajax(nivel, tamanho, tipo_pares){
	var url = "http://localhost:4567/paresjogo/"+nivel+"/"+tamanho+"/"+tipo_pares;
	$.get(url, embaralha);
	
}

function getNivel(){
	return getChecked(document.getElementsByName("niveis"));
}

function getTamanho(){
	return getChecked(document.getElementsByName("tamanhos"));
}

function getTipoPares(){
	return getChecked(document.getElementsByName("tipo_pares"));
}

function getChecked(vetor){
	for (var i = 0; i < vetor.length; i++) {
		if (vetor[i].checked) {
			return vetor[i].value;
		}
	}
}