package controladores;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import dao.ParDAO;

public class ParesJogo_Controlador implements Route{

	@Override
	public Object handle(Request req, Response resp) throws Exception {
		resp.header("Access-Control-Allow-Origin", "*");
		int id_user = req.session().attribute("user");
		ParDAO dao = new ParDAO();
		int nivel = Integer.parseInt(req.params("nivel"));
		int quant = Integer.parseInt(req.params("quant"));
		int teste = Integer.parseInt(req.params("teste"));
		return dao.randomPares(nivel, quant, id_user, teste);
	}

}
