package controlador;

import DAO.*;
import model.*;
import spark.*;

public class controladorAdiciona implements TemplateViewRoute	{
	
	public class Salvar implements Route{

		public Object handle(Request req, Response resp) throws Exception {
			String login = req.queryMap("login").value();
			String email = req.queryMap("email").value();
			String senha = req.queryMap("senha").value();
			
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			
			UsuarioDAO dao = new UsuarioDAO();
			dao.insert(usuario);
			
			
			resp.redirect("/");
			return null;
		}
			
	}

	@Override
	public ModelAndView handle(Request req, Response resp) {
		
		return new ModelAndView (null, "cadastro.html");
	}
	
	
}
