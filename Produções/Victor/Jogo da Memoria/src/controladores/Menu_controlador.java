package controladores;

import dao.UsuarioDAO;
import modelos.Usuario;
import spark.*;

public class Menu_controlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		try{
			UsuarioDAO dao = new UsuarioDAO();
			Usuario user = new Usuario(dao.selectUsuario(req.session().attribute("user")));
			return new ModelAndView(user, "menu.html");
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}