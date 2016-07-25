package web;

import controlador.*;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

	public static void main(String[] args) throws Exception {
		//Dicionario dicionario = new Dicionario();
		Spark.staticFileLocation("/publico");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("apresentacao");
		
		controladorCadastro cadastro = new controladorCadastro();
		Route salvarCadastro = cadastro.salvar;
		TemplateViewRoute mostraCadastro = cadastro.mostrar;
		controladorAdiciona login = new controladorAdiciona();
		controladorHome home = new controladorHome();
		TemplateViewRoute mostraHome = home.mostrar;
		
		Spark.get("/Cadastro", mostraCadastro, engine);
		Spark.post("/Cadastro", salvarCadastro);
		Spark.get("/Login", login, engine);
		Spark.get("/home", mostraHome, engine);

		
	}

}
