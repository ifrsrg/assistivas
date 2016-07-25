package web;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import banco.JDBCSelect;


public class controladorCadastro {
	public final Route salvar = new Salvar();
	public final TemplateViewRoute mostrar = new Mostrar();
	
	public class Salvar implements Route{

		public Object handle(Request req, Response resp) throws Exception {
			String login = req.queryMap("login").value();
			String senha = req.queryMap("senha").value();
			
			while(JDBCSelect.loga(login, senha) != true){
				resp.redirect("/");
				return login+","+senha;
			}
			resp.redirect("/home");
			return null;
		}	
	}
	
	public class Mostrar implements TemplateViewRoute{

		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "login.html");
			}
		
		}
}
