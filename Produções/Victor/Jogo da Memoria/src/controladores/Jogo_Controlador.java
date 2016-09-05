package controladores;

import spark.*;

public class Jogo_Controlador {
	
	private final Route salvar = new Salvar();
	private final TemplateViewRoute mostrar = new Mostrar();
	
	public class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request req, Response resp) {
			return new ModelAndView(null, "jogo.html");
		}
		
	}
	
	public class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {

			return null;
		}
		
	}
	
	public Route getRoute(){
		return salvar;
	}
	
	public TemplateViewRoute getTemplateView(){
		return mostrar;
	}
}
