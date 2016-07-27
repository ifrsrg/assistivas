package web;

import controlador.*;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

	public static void main(String[] args) throws Exception {
		Spark.staticFileLocation("/publico");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("apresentacao");
		
		controladorCadastro cadastro = new controladorCadastro();
		Route salvarCadastro = cadastro.salvar;
		TemplateViewRoute mostraCadastro = cadastro.mostrar;
		
		controladorLogin login = new controladorLogin();
		Route salvarLogin = login.salvar;
		TemplateViewRoute mostraLogin = login.mostrar;
		
		controladorHome home = new controladorHome();
		TemplateViewRoute mostraHome = home.mostrar;
		
		Spark.get("/login", mostraLogin, engine);
		Spark.post("/login", salvarLogin);
		
		Spark.get("/cadastro", mostraCadastro, engine);
		Spark.post("/cadastro", salvarCadastro);
		
		Spark.get("/home", mostraHome, engine);
		controladorUpload upador =	new controladorUpload();
		Spark.post("/recebeaudio", upador);

		
	}

}
