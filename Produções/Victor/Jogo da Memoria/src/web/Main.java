package web;

import controladores.*;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
		Spark.staticFileLocation("/pub");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("htmls");
		
		Home_controlador home = new Home_controlador();
				
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
		
	}

}
