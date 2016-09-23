package web;

import java.net.URLEncoder;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import controladores.Cadastro_Controlador;
import controladores.Home_controlador;
import controladores.Jogo_Controlador;
import controladores.JsonTransformer;
import controladores.Menu_controlador;
import controladores.Offset_Controlador;
import controladores.ParesJogo_Controlador;
import controladores.Pares_Controlador;
import controladores.UploadsListaControlador;
import controladores.Usuarios_Controlador;

public class Main {

	public static void main(String[] args) {
				
		Spark.staticFileLocation("/pub");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("htmls");
		JsonTransformer jsonengine = new JsonTransformer();
		
		Filter logado = new Filter(){

			@Override
			public void handle(Request req, Response resp) throws Exception {
				if (req.session().attribute("user") == null) {
					String erro = URLEncoder.encode("Você deve estar logado", "UTF-8");
					resp.redirect("/home?erro=" + erro);
				}
			}
		};
		
		Spark.before("/menu", logado);
		Spark.before("/meusUploads", logado);
		Spark.before("/novoPar", logado);
		Spark.before("/offsets/:offset", logado);
		
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
		
		Usuarios_Controlador users = new Usuarios_Controlador();
		
		Spark.get("/users/:login", "application/json", users, jsonengine);
		
		Offset_Controlador offsets = new Offset_Controlador();
		
		Spark.get("/offsets/:offset", "application/json", offsets, jsonengine);
		
		Jogo_Controlador jogo_controlador = new Jogo_Controlador();
		
		Spark.get("/jogar", jogo_controlador.getTemplateView(), engine);
		
		ParesJogo_Controlador pares_jogo = new ParesJogo_Controlador();
		
		Spark.get("/paresjogo/:nivel/:quant/:teste", "application/json", pares_jogo, jsonengine);
	}
}