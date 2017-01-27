package controladores;

import java.util.HashMap;

import spark.*;

public class Jogo_Controlador {
	
	private final Route salvar = new Salvar();
	private final TemplateViewRoute mostrar = new Mostrar();
	
	public class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request req, Response resp) {
			
			Boolean teste;
			
			if (req.session().attribute("user") != null) {
				teste = true;
			}else{
				teste = false;
			}
			
			HashMap dados = new HashMap();
			dados.put("logado", teste);
			
			return new ModelAndView(dados, "jogo.html");
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
