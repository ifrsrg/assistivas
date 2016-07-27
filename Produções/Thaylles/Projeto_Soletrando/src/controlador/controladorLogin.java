package controlador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import banco.JDBCSelect;
import DAO.*;

public class controladorLogin {
	public final Route salvar = new Salvar();
	public final TemplateViewRoute mostrar = new Mostra();
	
	public class Salvar implements Route{

		public Object handle(Request req, Response resp) throws Exception {
			String login = req.queryMap("login").value();
			String senha = req.queryMap("senha").value();
			Login usuario = new Login(login, senha);
			
			if(JDBCSelect.loga(usuario) == true){
				resp.redirect("/game");
				return null;
			}
			else{
				resp.redirect("/login/erro");
				return null;
			}
		}	
	}

	public class Mostra implements TemplateViewRoute{
		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "login.html");
			}
	}
}
