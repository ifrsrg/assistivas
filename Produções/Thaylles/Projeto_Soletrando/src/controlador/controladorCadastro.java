package controlador;

import DAO.UsuarioDAO;
import model.Usuario;
import spark.*;

public class controladorCadastro{
	
	public final Route salvar = new Salvar();
	public final TemplateViewRoute mostrar = new Mostra();
	
	public class Salvar implements Route{

		public Object handle(Request req, Response resp) throws Exception {
			String login = req.queryMap("login").value();
			String email = req.queryMap("email").value();
			String senha = req.queryMap("senha").value();
			
			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			UsuarioDAO dao = new UsuarioDAO();
			dao.insert(usuario);
			
			resp.redirect("/home");
			return null;
		}
			
	}
	public class Mostra implements TemplateViewRoute{
	public ModelAndView handle(Request req, Response resp) {
		return new ModelAndView (null, "cadastro.html");
	}
	
	
	}
	
}
