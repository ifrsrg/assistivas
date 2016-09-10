package controlador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import model.*;
import DAO.*;

public class controladorLogin {
	public final Route salvar = new Salvar();
	public final TemplateViewRoute mostrar = new Mostra();
	
	public class Salvar implements Route{

		public Object handle(Request req, Response resp) throws Exception {
			String login = req.queryMap("login").value();
			String senha = req.queryMap("senha").value();
			Login cliente = new Login();
			
			cliente.setLogin(login);
			cliente.setSenha(senha);
			
			System.out.print(req.attributes());
			
			int identificador = UsuarioDAO.verificador(cliente);
			
			if(UsuarioDAO.select(identificador) != null){
				req.session().attribute("user", identificador);
				System.out.print(req.attributes());
				System.out.print(req.session().attribute("user"));
				resp.redirect("/login/home");
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
