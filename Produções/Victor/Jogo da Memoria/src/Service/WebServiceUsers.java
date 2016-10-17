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
	
	

}