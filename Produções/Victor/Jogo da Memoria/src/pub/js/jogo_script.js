var tabuleiro = [];
var pares = [];
var selecionadas = [];
var viradas = 0;

var nivel, tamanho, tipo_pares;

var menu = "";

$(document).ready(function(){
	
	checar();
	
	menu = document.getElementsByClassName("container-fluid")[0].innerHTML;
		
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
    
    $(document).on("click", ".pos-jogo", function(){
    	$("h1:first").html("Jogo da Memória");
    	$("#opcoes").remove();
    });
    
    $(document).on("click", "#btn-1", function(){
    	toMenu();
    });
    
    $(document).on("click", "#btn-2", function(){
    	montar();
    });
    
    $(document).on("click", "#btn-3", function(){
    	Ajax(nivel, tamanho, tipo_pares);
    });
    
    $(document).on("click", "#btn-4", function(){
    	jogar_novamente();
    });
    
});

function carregarJogo(){
	nivel = getNivel();
    tamanho = getTamanho();
    tipo_pares = getTipoPares();
    Ajax(nivel, tamanho, tipo_pares);
}



function montar(){ //monta o HTML através do innerHTML
    var linhas, colunas,change = "";
    var elemento = document.getElementById("jogo");
    
    $("#jogo").removeClass("col-sm-offset-4");
    $("#jogo").addClass("col-sm-offset-1");
    $("#jogo").removeClass("col-sm-4");
    $("#jogo").addClass("col-sm-10");
    $("#jogo").css("padding-bottom", "15px");

    //elemento.id = "jogo";


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
                	selecionadas[i].innerHTML += "<input type='button' class='info' data-toggle='modal' data-target='.mymodal'" +
                			"					  data-id="+$(selecionadas[i]).attr("data-id")+"></div>";
            	}
                if (viradas === pares.length){
                    venceu();
                    viradas = 0;
                }
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
    
    document.getElementsByClassName("container-fluid")[0].innerHTML = menu;
        
    checar();
}

function venceu(){
    document.getElementsByTagName("h1")[0].innerHTML = "Você Venceu";
    
    var add = "<div class='text-center' id='opcoes'><input  type='button' id = 'btn-2' class='btn btn-default pos-jogo' value='Jogar com mesmos pares'>" +
			  "<input type='button' id = 'btn-3' class='btn btn-default pos-jogo' value='Jogar com outros pares'>" +
			  "<input type='button' id = 'btn-4' class='btn btn-default pos-jogo' value='Trocar dificuldade'>";
    
    if (logado()) {
    	add += "<input type='button' id='btn-1' class='btn btn-default pos-jogo' value='Voltar ao menu'>";
	}
    
    add += "</div>"
    
    $(".container-fluid:first").append(add);
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
	if ($(".hidden:first").html() != 'true') {
		$("#3B").click();
	}

}