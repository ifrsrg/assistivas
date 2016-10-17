package controladores;

import spark.Request;
import spark.Response;
import spark.Route;
import dao.ParDAO;

public class ParesJogo_Controlador implements Route{

	@Override
	public Object handle(Request req, Response resp) throws Exception {
		Integer id_user = req.session().attribute("user");
		ParDAO dao = new ParDAO();
		int nivel = Integer.parseInt(req.params("nivel"));
		int quant = Integer.parseInt(req.params("quant"));
		int valor_teste = Integer.parseInt(req.params("teste"));
		boolean teste = true;
		if (valor_teste == 1) teste = false;
		if (teste) {
			System.out.println("funfo");
		}else{
			System.out.println("sad");
		}
		System.out.println(nivel + "-" + quant + "-" + teste);
		if (id_user == null || teste) {
			if (nivel != 0)
				return dao.randomPares(nivel, quant);
			else
				return dao.randomPares(quant);
		}else{
			if (nivel != 0) 
				return dao.randomPares(id_user, nivel, quant);
			else
				return dao.randomParesUser(id_user, quant);
		}
	}
}