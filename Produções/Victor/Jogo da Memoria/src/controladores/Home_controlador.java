package controladores;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import dao.UsuarioDAO;

public class Home_controlador{

	private final TemplateViewRoute mostrar = new Mostrar();
	private final Route salvar = new Salvar();

	class Mostrar implements TemplateViewRoute{
		public ModelAndView handle(Request req, Response resp) {
			
			HashMap dados = new HashMap();
			
			if (req.queryMap("erro") != null)
				dados.put("erro", req.queryParams("erro"));
						
			return new ModelAndView(dados, "home.html");
		}
	}
	
	class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			
			UsuarioDAO dao = new UsuarioDAO();
			
			String login = req.queryMap("login").value();
			String senha = req.queryMap("senha").value();
			
			int verificacao = dao.verificaUsuario(login, senha);
			
			if (verificacao < 0) {
				resp.redirect("/home");
			}else{
				req.session().attribute("user", verificacao);
				
				resp.redirect("/menu");
			}
			return null;
		}
	}

	public TemplateViewRoute getTemplateView(){
		return mostrar;
	}
	
	public Route getRoute(){
		return salvar;
	}
}