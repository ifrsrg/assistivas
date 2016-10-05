package controlador;

import controlador.controladorCadastro.Mostra;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class controladorJogo {

	public final TemplateViewRoute mostrar = new Mostra();
	
	public class Mostra implements TemplateViewRoute{
	
		public ModelAndView handle(Request req, Response resp) {
		return new ModelAndView (null, "jogo.html");
		}
	}
}
