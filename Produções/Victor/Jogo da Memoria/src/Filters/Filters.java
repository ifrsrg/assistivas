package Filters;

import java.net.URLEncoder;

import dao.ParDAO;
import modelos.Par;
import spark.Filter;
import spark.Request;
import spark.Response;

public class Filters {

	public final Filter logado = new Filter(){
		
		@Override
		public void handle(Request req, Response resp) throws Exception {
			if (req.session().attribute("user") == null) {
				String erro = URLEncoder.encode("Você deve estar logado", "UTF-8");
				resp.redirect("/home?erro=" + erro);
			}
		}
	};
	
	public final Filter dono_par = new Filter(){

		@Override
		public void handle(Request req, Response resp) throws Exception {
			ParDAO dao = new ParDAO();
			
			Par par = dao.selectByData(req.params("data"));
			
			if (par.getId() != req.session().attribute("user")) {
				resp.redirect("/menu");
			}
		}		
	};
}
