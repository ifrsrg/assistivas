package controladores;

import spark.Request;
import spark.Response;
import spark.Route;

import dao.ParDAO;

public class Offset_Controlador implements Route{

	@Override
	public Object handle(Request req, Response resp) throws Exception {
		resp.header("Content-Type", "application/json");
		resp.header("Access-Control-Allow-Origin", "*");
		int offset = Integer.parseInt(req.params("offset"));
		int id = req.session().attribute("user");
		ParDAO dao = new ParDAO();
		return dao.offsets(offset, id);
	}	
}