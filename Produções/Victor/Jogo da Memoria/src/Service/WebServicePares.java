package Service;

import controladores.JsonTransformer;
import dao.ParDAO;
import spark.Request;
import spark.Response;
import spark.Route;

public class WebServicePares extends WebService{
	
	ParDAO dao = new ParDAO();

	public WebServicePares() {
		super("application/json", new JsonTransformer());
	}
	
	public final Service offsets = new Service() {

		@Override
		public Object deal(Request req, Response resp) {
			int offset = Integer.parseInt(req.params("offset"));
			int id = req.session().attribute("user");
			return dao.offsets(offset, id);
		}
		
	};

	public final Service paresJogo = new Service(){

		@Override
		public Object deal(Request req, Response resp) {
			Integer id_user = req.session().attribute("user");
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
		
	};

	public final Service delete = new Service(){

		@Override
		public Object deal(Request req, Response resp) throws Exception {
			return dao.delete(req.params("id"));
		}
		
	};
	
}
