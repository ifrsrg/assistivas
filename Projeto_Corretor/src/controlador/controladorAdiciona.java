package controlador;

import spark.*;

public class controladorAdiciona implements TemplateViewRoute	{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		
		return new ModelAndView (null, "corrigindo.html");
	}
	
	
}
