package controlador;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import DAO.AdicionaDAO;

public class textoControlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		String texto = AdicionaDAO.load(Integer.parseInt(req.params("numero")));
		HashMap dados = new HashMap();	
		dados.put("texto", texto);	
		return new ModelAndView(dados, "texto.html");
	}
}
