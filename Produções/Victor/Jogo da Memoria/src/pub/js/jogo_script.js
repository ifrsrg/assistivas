var tabuleiro = [];
var pares = [];
var selecionadas = [];

function carregarJogo(){
	var nivel = getNivel();
	var tamanho = getTamanho();
	var tipo_pares = getTipoPares();
	Ajax(nivel, tamanho, tipo_pares);
}

function montar(tamanho){ //monta o HTML através do innerHTML
    var nivel = getNivel();
    var tamanho = getTamanho();
    var linhas, colunas,change = "";
    var elemento = document.getElementsByTagName("table")[0];

    switch(tamanho){
        case "6":  linhas = 2;
                   colunas = 6; break;
        case "10": linhas = 3;
                   colunas = 8; break;
        case "12": linhas = 3;
                   colunas = 10;
    }    
    
    var count = 0;
    //vetor para saber quantas vezes cada par foi posicionado
    posicionados = [];

    for(var i = 0; i < linhas; i++){
        change += "<tr>";
        for(var j = 0; j < colunas; j++){
            change += "<td></td>";
        }
    }

    elemento.innerHTML = change;

    var posicoes = document.getElementsByTagName("td");
    var vezes = 0;
    while(count < tamanho){
        var random_num = parseInt(Math.random()*(tamanho*2));
        var pos = posicoes[random_num];
        if (pos.innerHTML === "") {
            if (vezes ==  0) {
                console.log(pares[count]);
                pos.innerHTML = "<img class='carta' data-ext = 0 data-id = "+pares[count].nome+
                                " src='image/cover.jpg'></td>";
            }else{
                pos.innerHTML = "<img class='carta' data-ext = 1 data-id = "+pares[count].nome+
                                " src='image/cover.jpg'></td>";
            }
            tabuleiro[random_num] = pares[count];
            vezes++;
        }
        if (vezes == 2) {
            vezes = 0;
            count++;
        }
    }
}

function carregaPares(vetor){
    pares = JSON.parse(vetor);
    if (pares.length < getTamanho()) {
        window.location = "http://localhost:4567/menu";
    }else{
        montar();
    }
}

function forma_nome(par, ext){
	return par.id + "_" + par.nome + "_" + par.data + "." + ext;
}

/*function joga(element){
	if (selecionadas.length === 2 && tabuleiro[selecionadas[0]] !== tabuleiro[selecionadas[1]]){
        clearTimeout(tenta);
        limpa();
    }        
    //var elemento = document.getElementById(numero);
    if (elemento.src.endsWith("cover.jpg")){
        elemento.src = "imagens/" + pares[tabuleiro[numero]].nome;
        selecionadas[selecionadas.length] = numero;
        if (selecionadas.length === 2){
            if (tabuleiro[selecionadas[0]] !== tabuleiro[selecionadas[1]]){
                tenta = setTimeout(function(){limpa();}, 3000);
                if (jogador_atual === 1) jogador_atual = 0;
                else jogador_atual++;
            }else{
                new Audio("sons/"+tabuleiro[selecionadas[0]]+".ogg").play();
                jogadores[jogador_atual]++;
                selecionadas = [];
            }
        }
    }
    if ((jogadores[0]) + (jogadores[1]) === tabuleiro.length/2){
        tabuleiro=[];
        if (jogadores[0] > jogadores[1])
            vencedor = "<h2>Jogador1 ganhou<br>"+jogadores[0]+" Pontos</h2>";
        if (jogadores[0] < jogadores[1])
            vencedor = "<h2>Jogador2 ganhou<br>"+jogadores[1]+" Pontos</h2>";
        if (jogadores[0] === jogadores[1])
            vencedor = "<h2>Empate<br>"+jogadores[0] + " Pontos</h2>";
        audio[modo].pause();
        modo = 0;
        montar();
    }
        atualizaPlayers();

}
}
*/

function Ajax(nivel, tamanho, tipo_pares){
	var url = "http://localhost:4567/paresjogo/"+nivel+"/"+tamanho+"/"+tipo_pares;
	$.get(url, carregaPares);
	
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