package controlador;

import DAO.AdicionaDAO;
import concatenateWAV.ConcatenateWAV;
import distance.Levenshtein;
import spark.*;

public class controladorHome {

	public final Route salvar = new Salvar();
	public final TemplateViewRoute mostrar = new Mostrar();
	
	public class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			String[] vetor = req.queryMap("texto").value().split(" ");
			for (int i = 0; i < vetor.length; i++) {
				String lev = Levenshtein.distance(vetor[i]);
				if (Levenshtein.distance(vetor[i]) != null) {
					String[] lev_vetor = lev.split(" ");
					AdicionaDAO.Salva(lev);
					for (int j = 0; j < lev_vetor.length; j++) {
						ConcatenateWAV.soletrar(lev_vetor[j]);
					}
				}
			}
			
			resp.redirect("/corrigindo");
			return null;
		}
			
	}
	
	public class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "novoTexto.html");
		}
		
	}
}
