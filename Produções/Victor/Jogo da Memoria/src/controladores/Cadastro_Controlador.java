package controladores;

import dao.UsuarioDAO;
import spark.*;
import modelos.Usuario;

public class Cadastro_Controlador {

	private final Route salvar =  new Salvar();
	private final TemplateViewRoute mostrar = new Mostrar();
	
	class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			Usuario user = new Usuario(req.queryMap("nome").value(),
									   req.queryMap("email").value(),
									   req.queryMap("login").value(),
									   req.queryMap("senha").value());
			UsuarioDAO dao = new UsuarioDAO();
			dao.save(user);
			user.setId(dao.selectMax());
			req.session().attribute("user", user.getId());
			resp.redirect("/menu");
			return null;
		}	
	}
	
	class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "cadastro.html");
		}
		
	}
	
	
	public Route getRoute() {
		return salvar;
	}
	

	public TemplateViewRoute getTemplateView() {
		return mostrar;
	}
}
