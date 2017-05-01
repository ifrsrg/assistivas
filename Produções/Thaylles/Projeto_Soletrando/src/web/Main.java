package web;

import controlador.*;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;
public class Main {

	public static void main(String[] args) {
		
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
		
		controladorLogado logado = new controladorLogado();
		TemplateViewRoute mostraLogado = logado.mostrar;
		
		Spark.get("/login", mostraLogin, engine);
		Spark.post("/login", salvarLogin);
		
		Spark.get("/cadastro", mostraCadastro, engine);
		Spark.post("/cadastro", salvarCadastro);
		
		Spark.get("/home", mostraHome, engine);
		controladorUpload upador =	new controladorUpload();
		
		Spark.post("/recebeaudio", upador);
		Spark.get("/login/home", mostraLogado, engine);
		
		controladorRedireciona redireciona = new controladorRedireciona();
		TemplateViewRoute redirecionar = redireciona.mostrar;
		Spark.get("/", redirecionar, engine);
		
		TemplateViewRoute paginaUpload = upador.mostrar;
		Spark.get("/login/upload", paginaUpload, engine);
		
		controladorJogo jogo = new controladorJogo(); 
		TemplateViewRoute paginaJogo = jogo.mostrar;
		Spark.get("/login/jogo", paginaJogo , engine);
		
		controladorGravar gravar = new controladorGravar();
		TemplateViewRoute paginaGravar = gravar.mostrar;
		Spark.get("/login/gravar", paginaGravar, engine);
		
		Route gravarAudio = gravar.salvar;
		Spark.post("/recebegravar", gravarAudio);
		
		/*
		Filter logadoB = new Filter(){
			public void handle(Request req, Response resp){
				if(req.session().attribute("user") == null){
					try {
						String erro = URLEncoder.encode("Você deve estar logado", "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					resp.redirect("/home");
				}
			}
		};

		Spark.before("/login/jogo", logadoB);
		Spark.before("/login/upload", logadoB);
		Spark.before("/login/home", logadoB);	
		*/
		
		ContatosWebService ws = new ContatosWebService();
		
		Spark.get("/palavras/:palavra", ws.contentType, ws.selectRota, ws.responseTransformer);
		
		Spark.get("/palavras", ws.contentType, ws.selectAll, ws.responseTransformer);
		
	}
}
