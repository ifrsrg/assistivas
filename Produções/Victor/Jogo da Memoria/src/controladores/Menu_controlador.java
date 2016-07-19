package controladores;

import spark.*;

public class Menu_controlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request arg0, Response arg1) {
		return new ModelAndView(null, "menu.html");
	}

}
