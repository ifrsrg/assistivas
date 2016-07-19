package controladores;

import spark.*;

public class Pares_Controlador {
	
	private final Route salvar = new Salvar();
	private final TemplateViewRoute mostrar = new Mostrar();
	
	class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			resp.redirect("/menu");
			return null;
		}
		
		
	}
	
	class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "pares.html");
		}
		
	}

	public Route getRoute() {
		return salvar;
	}

	public TemplateViewRoute getTemplateView() {
		return mostrar;
	}
}
