package controlador;

import DAO.AdicionaDAO;
import DAO.Usuario;
import banco.JDBCSelect;
import concatenateWAV.ConcatenateWAV;
import distance.Levenshtein;
import spark.*;
import web.controladorCadastro.Mostrar;

public class controladorHome {

	public final TemplateViewRoute mostrar = new Mostrar();
	public class Mostrar implements TemplateViewRoute{

		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "home.html");
			}
		
		}
}
