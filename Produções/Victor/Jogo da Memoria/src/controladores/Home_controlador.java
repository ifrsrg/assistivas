package controladores;

import spark.*;

import java.sql.*;

public class Home_controlador{

	private final TemplateViewRoute mostrar = new Mostrar();
	private final Route salvar = new Salvar();

	class Mostrar implements TemplateViewRoute{
		public ModelAndView handle(Request req, Response resp) {

			
			return new ModelAndView(null, "home.html");
		}
	}
	
	class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			
			resp.redirect("/menu");
			return null;
		}
		
	}

	public TemplateViewRoute getTemplateView(){
		return mostrar;
	}
	
	public Route getRoute(){
		return salvar;
	}

}
