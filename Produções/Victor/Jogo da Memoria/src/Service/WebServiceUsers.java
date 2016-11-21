package Service;

import controladores.JsonTransformer;
import dao.UsuarioDAO;
import spark.Request;
import spark.Response;

public class WebServiceUsers extends WebService{
	
	UsuarioDAO dao = new UsuarioDAO();

	public WebServiceUsers() {
		super("Content-Type", new JsonTransformer());
	}
	
	public final Service userLogin = new Service(){

		@Override
		public Object deal(Request req, Response resp) throws Exception {
			return dao.selectLogin(req.params("login"));
		}
	};
	
	public final Service logado = new Service(){

		@Override
		public Object deal(Request req, Response resp) throws Exception {
			Integer user = req.session().attribute("user");
			if (user == null){
				return false;
			}
			else{
				return true;
			}
		}
	};
}