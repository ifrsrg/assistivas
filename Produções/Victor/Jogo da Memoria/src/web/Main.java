package web;

import Filters.Filters;
import Service.WebServicePares;
import Service.WebServiceUsers;
import controladores.Cadastro_Controlador;
import controladores.EditarPar_Controlador;
import controladores.Home_controlador;
import controladores.Jogo_Controlador;
import controladores.Menu_controlador;
import controladores.Pares_Controlador;
import controladores.UploadsListaControlador;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
		Spark.staticFileLocation("/pub");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("htmls");
		
		WebServicePares wsp = new WebServicePares();
		WebServiceUsers wsu = new WebServiceUsers();
		
		Filters filters = new Filters();
		
		Spark.before("/menu", filters.logado);
		Spark.before("/meusUploads", filters.logado);
		Spark.before("/novoPar", filters.logado);
		Spark.before("/offsets/:offset", filters.logado);
		Spark.before("/editarPar/:data", filters.logado);
		Spark.before("/editarPar/:data", filters.dono_par);
		
		Home_controlador home = new Home_controlador();
		
		Spark.get("/", home.getTemplateView(), engine);
				
		Spark.get("/home", home.getTemplateView(), engine);
		Spark.post("/home", home.getRoute());
		
		Menu_controlador menu = new Menu_controlador();
		
		Spark.get("/menu", menu, engine);
		
		UploadsListaControlador uploads_lista = new UploadsListaControlador();
		
		Spark.get("/meusUploads", uploads_lista, engine);
		
		Cadastro_Controlador cadastro = new Cadastro_Controlador();
		
		Spark.get("/cadastro", cadastro.getTemplateView(), engine);
		
		Spark.post("/cadastro", cadastro.getRoute());
		
		Pares_Controlador pares = new Pares_Controlador();
		
		Spark.get("/novoPar", pares.getTemplateView(), engine);
		
		Spark.post("/novoPar", pares.getRoute());
		
		Jogo_Controlador jogo_controlador = new Jogo_Controlador();
		
		Spark.get("/jogar", jogo_controlador.getTemplateView(), engine);
		
		EditarPar_Controlador editarPar = new EditarPar_Controlador();
		
		Spark.get("/editarPar/:data", editarPar.getMostrar(), engine);
		
		Spark.post("/editarPar/:data", editarPar.getSalvar());
		
		Spark.get("/users/:login", wsu.contentType, wsu.userLogin, wsu.responseTransformer);
		
		Spark.get("/logado", wsu.contentType, wsu.logado, wsu.responseTransformer);
		
		Spark.get("/offsets/:offset", wsp.contentType, wsp.offsets, wsp.responseTransformer);
		
		Spark.get("/paresjogo/:nivel/:quant/:teste", wsp.contentType, wsp.paresJogo, wsp.responseTransformer);
		
		Spark.get("/selectAll", wsp.contentType, wsp.selectAll, wsp.responseTransformer);
		
		Spark.post("/remove/:id", wsp.delete);
	}
}