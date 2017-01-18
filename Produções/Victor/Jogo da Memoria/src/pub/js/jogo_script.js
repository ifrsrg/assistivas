var tabuleiro = [];
var pares = [];
var selecionadas = [];
var viradas = 0;

$(document).ready(function(){
	
	checar();
	
    $(document).on("click", "#jogar", function(){
    	if (getTamanho() != undefined && getNivel() != undefined && getTipoPares() != undefined){
	        carregarJogo();
    	}
    });

    $(document).on("click", ".posicoes", function(){
    	if (selecionadas.length != 2) {
            joga(this);
		}
    });

    $(document).on("click", "#novo", function(){
        jogar_novamente();
    });

    $(document).on("click", "#voltar", function(){
        toMenu();
    });
    
    $(document).on("click", "#3A", function(){
    	checar();
    });
    
    $(document).on("click", ".info", function(){
    	info(this);
    });
    
    $(document).on("click", "#banner > .info", function(){
    	$("#banner").slideToggle("slow");
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
  //document.getElementById("bloco").innerHTML = "<table></table>";
    var elemento = document.getElementById("bloco");
    
    $("#bloco").removeClass("col-sm-offset-4");
    $("#bloco").addClass("col-sm-offset-1");
    $("#bloco").removeClass("col-sm-4");
    $("#bloco").addClass("col-sm-10");
    $("#bloco").css("padding-bottom", "15px");

    elemento.id = "jogo";


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

    var total = colunas * linhas;
    
    for (var i = 0; i < total; i++) {
		change += "<div class='posicoes' virado=false></div>";
	}
    
    elemento.innerHTML = change;
    
    var posicoes = document.getElementsByClassName("posicoes");
    
    var vezes = 0;
    while(count < tamanho){
        var random_num = parseInt(Math.random()*(tamanho*2));
        var pos = posicoes[random_num];
        if (pos.innerHTML === "") {
            pos.setAttribute("data-id", count);
            if (vezes ==  0){
                pos.setAttribute("data-ext", 0);
            	pos.innerHTML = "<img class='carta' src='covers/image_cover.jpg'>";
            }else{
                pos.setAttribute("data-ext", 1);
            	pos.innerHTML = "<img class='carta' src='covers/video_cover.png'>";
            }
            tabuleiro[random_num] = pares[count];
            vezes++;
        }
        if (vezes == 2) {
            vezes = 0;
            count++;
        }
    }
    
    /*for(var i = 0; i < linhas; i++){
        change += "<tr>";
        for(var j = 0; j < colunas; j++){
            change += "<td class='posicoes' virado=false></td>";
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
            if (vezes ==  0){
                pos.setAttribute("data-ext", 0);
            	pos.innerHTML = "<img class='carta' src='covers/image_cover.jpg'>";
            }else{
                pos.setAttribute("data-ext", 1);
            	pos.innerHTML = "<img class='carta' src='covers/video_cover.png'>";
            }
            tabuleiro[random_num] = pares[count];
            vezes++;
        }
        if (vezes == 2) {
            vezes = 0;
            count++;
        }
    }*/
}

function carregaPares(vetor){
    pares = JSON.parse(vetor);
    if (pares.length < getTamanho()) {
        toMenu();
    }else{
        montar();
    }
}

function forma_nome(par, ext){
    return par.id + "_" + rename(par.nome) + "_" + par.data + "." + ext + "?v="+new Date().getTime();
}

function rename(nome){
	var res =  nome.replace(new RegExp(" ", 'g'), "_");
	return res;
}

function joga(element){
    var numero = $(element).attr("data-id");
    if ($(element).attr("virado") == 'false' && 
    	($(element).attr("data-ext") != $(selecionadas[0]).attr("data-ext") || selecionadas.length == 0)){
        var item = element.children[0];
        var ext = element.getAttribute("data-ext");
        if (ext == 0) {
            item.src = "image/" + forma_nome(pares[numero], pares[numero].form_img);
        }else{
            element.innerHTML = "<video class='carta' src='/video/" + forma_nome(pares[numero], "ogg") + "' controls autoplay muted></video>";
        }
        $(element).attr("virado", true);
        selecionadas[selecionadas.length] = element;
        if (selecionadas.length === 2){
            if ($(selecionadas[0]).attr("data-id") !== $(selecionadas[1]).attr("data-id")){
            	$("#jogo").css("background-color", "red");
            	achou = setTimeout(function(){limpa(false);}, 3000);
            }else{
                viradas++;
                $("#jogo").css("background-color", "green");
            	achou = setTimeout(function(){limpa(true);}, 3000);
            	for (var i = 0; i < 2; i++) {
                	selecionadas[i].innerHTML += "<button type='button' class='info' data-toggle='modal' data-target='.mymodal'" +
                			"					  data-id="+$(selecionadas[i]).attr("data-id")+"></div>";
            	}
                if (viradas === pares.length)
                    venceu();
                selecionadas = [];
            }
        }
    }
}

function info(element){
	var par = pares[$(element).parent().attr("data-id")];
	var nome = normaliza_nome(par.nome);
	var array = nome.split('');

	$("#palavra").html(nome);
	$("#imagem").attr("src", "image/" + forma_nome(par, par.form_img));
	$("#video").attr("src", "video/" + forma_nome(par, "ogg"));
	
	var alfabeto = $("#alfabeto");
	
	alfabeto.empty();
	
	for(var i = 0; i < nome.length; i++){
		alfabeto.append("<img class='media-object alfabeto' src='/alfabeto/" + nome[i] + ".png'>")
	}
}

function normaliza_nome(nome){
    nome.replace(new RegExp('á', 'g'), 'a');
    nome.replace(new RegExp('é', 'g'), 'e');
    nome.replace(new RegExp('â', 'g'), 'a');
    nome.replace(new RegExp('ê', 'g'), 'e');
    nome.replace(new RegExp('ã', 'g'), 'a');
    nome.replace(new RegExp('ú', 'g'), 'u');
    return nome;
}

function limpa(estado){
	$("#jogo").css("background-color", "#FFB600");
	if (estado == false) {	
	    for (var i = 0; i < selecionadas.length; i++) {
	    	var element = $(selecionadas[i]);
	        element.attr("virado", false);
	        if (element.attr("data-ext") == '0') {
	        	element.html("<img class='carta' src='covers/image_cover.jpg'>"); 
			}else{
				element.html("<img class='carta' src='covers/video_cover.png'>");  
			}
	    }
	    selecionadas=[];
	}
}

function mudaCor(achou){
	var tabuleiro = $("#jogo");
	if (estado = 'true') {
		tabuleiro.css("background-color", "green");
	}else{
		tabuleiro.css("background-color", "red");
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
    checar();
}

function venceu(){
    document.getElementsByTagName("h1")[0].innerHTML = "Você Venceu";
    var body = document.getElementById("change");
    body.innerHTML += "<button id='novo'>Jogar Novamente</button><button id='voltar'>Voltar ao menu</button>";
}


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

function toMenu(){
    window.location = "http://localhost:4567/menu";
}

function checar(){
	$.getJSON("http://localhost:4567/logado", verifica);
}

function verifica(teste){
	if (teste != true) {
		$("#3B").click();
	}
}