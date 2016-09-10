package controlador;
import DAO.UsuarioDAO;
import controlador.*;
import distance.Levenshtein;
import spark.*;

public class controladorHome {

	public final TemplateViewRoute mostrar = new Mostrar();
	public class Mostrar implements TemplateViewRoute{

		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "home.html");
			}
		
		}
}
