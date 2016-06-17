package web;

import spark.*;
import spark.template.mustache.MustacheTemplateEngine;
import controlador.*;
import dicionario.Dicionario;

public class Main {

	public static void main(String[] args) throws Exception {
		Dicionario dicionario = new Dicionario();
		Spark.staticFileLocation("/publico");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("apresentacao");
		
		controladorHome home = new controladorHome();
		Route salvarHome = home.salvar;
		TemplateViewRoute mostrarHome = home.mostrar;
		
		Spark.get("/", mostrarHome, engine);
		Spark.post("/", salvarHome);
		
		controladorAdiciona add = new controladorAdiciona();
		
		Spark.get("/corrigindo", add, engine);
		
		
		textoControlador text = new textoControlador();
		
		Spark.get("/:numero", text, engine);
		
	}

}
