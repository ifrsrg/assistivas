var tabuleiro = [];
var pares = [];
var selecionadas = [];
var viradas = 0;

$(document).ready(function(){

    $("#jogar").on("click", function(){
    	alert("hey you");
        carregarJogo();
    });

    $(document).on("click", ".posicoes", function(){
        joga(this);
    });

    $(document).on("click", "#novo", function(){
    	alert("rebel rebel");
        jogar_novamente();
    });

    $(document).on("click", "#voltar", function(){
    	alert("changes");
        toMenu();
    });

});

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

    elemento.id = "jogo";

    alert(tamanho);

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

    var num_pos = 0;

    for(var i = 0; i < linhas; i++){
        change += "<tr>";
        for(var j = 0; j < colunas; j++){
            change += "<td class='posicoes'></td>";
        }
    }

    elemento.innerHTML = change;

    var posicoes = document.getElementsByTagName("td");
    var vezes = 0;
    while(count < tamanho){
        var random_num = parseInt(Math.random()*(tamanho*2));
        var pos = posicoes[random_num];
        if (pos.innerHTML === "") {
            pos.setAttribute("data-id", count);
            if (vezes ==  0) 
                pos.setAttribute("data-ext", 0);
            else
                pos.setAttribute("data-ext", 1);
            
            pos.innerHTML = "<img class='carta' src='image/cover.jpg'>";
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
    alert(pares);
    alert(getTamanho());
    if (pares.length < getTamanho()) {
        toMenu();
    }else{
        montar();
    }
}

function forma_nome(par, ext){
    return par.id + "_" + par.nome + "_" + par.data + "." + ext;
}

function joga(element){
    if (selecionadas.length === 2 && (tabuleiro[selecionadas[0]] !== tabuleiro[selecionadas[1]])) {
        alert("in");
        clearTimeout(tenta);
        limpa();
    }
    var numero = $(element).attr("data-id");
    var item = element.children[0];
    if (item.src.endsWith("cover.jpg")){
        var ext = element.getAttribute("data-ext");
        if (ext == 0) {
            item.src = "image/" + forma_nome(pares[numero], pares[numero].form_img);
        }else{
            element.innerHTML = "<video class='carta' autoplay><source src='video/"
                                 + forma_nome(pares[numero], pares[numero].form_vid) + "' type='video/"+
                                 pares[numero].form_vid+"'></video>";
        }
        selecionadas[selecionadas.length] = numero;
        if (selecionadas.length === 2){
            if (selecionadas[0] !== selecionadas[1]){
                tenta = setTimeout(function(){limpa();}, 3000);
            }else{
                alert("hey");
                viradas++;
                if (viradas === pares.length)
                    venceu();
                selecionadas = [];
            }
        }
    }
}

function jogar_novamente(){
    document.getElementsByTagName("h1")[0].innerHTML = "Jogo da Memória";
    document.getElementById("change").innerHTML = "<table><tr><th colspan='4'>Escolha o nível das palavras</th>"+
                                                  "</tr><tr><td><input type='radio' id='1A' name='niveis' value='1'><label for='1A'>Nível 1</label>"+
                                                  "</td><td><input type='radio' id='1B' name='niveis' value='2'><label for='1B'>Nível 2</label>"+
                                                  "</td><td><input type='radio' id='1C' name='niveis' value='3'><label for='1C'>Nível 3</label>"+
                                                  "</td><td><input type='radio' id='1D' name='niveis' value='0'><label for='1D'>Mesclado</label>"+
                                                  "</td></tr><tr><th colspan='4'>Escolha o número de pares</th></tr><tr><td><input type='radio' id='2A' name='tamanhos' value='6'>" +
                                                  "<label for = '2A'>6 pares</label></td><td><input type='radio' id='2B' name='tamanhos' value='10'>"+
                                                  "<label for = '2B'>10 pares</label></td><td><input type='radio' id='2C' name='tamanhos' value='12'>"+
                                                  "<label for = '2C'>12 pares</label></td><td><input type='radio' id='2D' name='tamanhos' value='14'>"+
                                                  "<label for = '2D'>14 pares</label></td></tr><tr><th colspan='4'>Você deseja...</th></tr><tr><td colspan='2'>"+
                                                  "<input type = 'radio' id='3A' name='tipo_pares' value='0'><label for='3A'>Jogar com meus pares</label></td>"+
                                                  "<td colspan='2'><input type = 'radio' id='3B' name='tipo_pares' value='1'><label for='3A'>Jogar com pares de outros jogadores</label>"+
                                                  "</td></tr><tr><th colspan = '4'><button id='jogar'>Jogar</button></th></tr>";
}

function venceu(){
    document.getElementsByTagName("h1")[0].innerHTML = "Você Venceu";
    var body = document.getElementById("change");
    body.innerHTML += "<button id='novo'>Jogar Novamente</button><button id='voltar'>Voltar ao menu</button>";
}

function limpa(){
    for (var i = 0; i < selecionadas.length; i++) {
        $("td[data-id="+ selecionadas[i] +"]").html("<img class='carta' src='image/cover.jpg'>");    
    }
    selecionadas=[];
}

function Ajax(nivel, tamanho, tipo_pares){
    var url = "http://localhost:4567/paresjogo/"+nivel+"/"+tamanho+"/"+tipo_pares;
    alert(url)
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

function toMenu(){
	alert("ó o danadao");
    window.location = "http://localhost:4567/menu";
}