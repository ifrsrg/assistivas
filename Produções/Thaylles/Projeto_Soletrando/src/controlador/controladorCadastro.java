package controlador;

import DAO.Usuario;
import banco.JDBCSelect;
import banco.insertBanco;
import spark.*;

public class controladorCadastro{
	
	public final Route salvar = new Salvar();
	public final TemplateViewRoute mostrar = new Mostra();
	
	public class Salvar implements Route{

		public Object handle(Request req, Response resp) throws Exception {
			String login = req.queryMap("login").value();
			String email = req.queryMap("email").value();
			String senha = req.queryMap("senha").value();
			
			insertBanco.adiciona(new Usuario(login,email,senha));
			
			resp.redirect("/home");
			return null;
		}
			
	}
	public class Mostra implements TemplateViewRoute{
	@Override
	public ModelAndView handle(Request req, Response resp) {
		
		return new ModelAndView (null, "cadastro.html");
	}
	
	
	}
	
}
