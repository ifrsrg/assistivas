package controladores;

import java.util.HashMap;

import modelos.Usuario;
import spark.*;

public class Menu_controlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		HashMap dados = new HashMap();
		dados.put("user", new Usuario(req.session().attribute("user")));
		return new ModelAndView(new Usuario(req.session().attribute("user")), "menu.html");
	}
}
