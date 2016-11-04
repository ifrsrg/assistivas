package web2;

import com.google.gson.Gson;

import DAO.AdicionaDAO;
import DAO.UploadDAO;
import model.Upload;
import spark.Request;
import spark.Response;
import spark.Route;

public class ContatosWebService extends WebService {
	
	private UploadDAO dao = new UploadDAO();
	private Gson gson = new Gson();
	
	public ContatosWebService() {
		super("application/json", new JsonTransformer());
	}

	public final Service selectRota = new Service() {
		public Object deal(Request request, Response response) throws Exception {
			String palavra = request.params("palavra");
			return dao.selectRota(palavra);
		}
	};
	
	public final Service insert = new Service() {
		public Object deal(Request request, Response response) throws Exception {
			String json = request.body();			
			// System.out.println(json);
			Upload palavra = gson.fromJson(json, Upload.class);
			dao.insertPalavras(palavra);
			return "";
		}
	};
	
	
}







