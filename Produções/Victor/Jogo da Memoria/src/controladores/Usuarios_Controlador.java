package controladores;

import dao.UsuarioDAO;
import spark.Request;
import spark.Response;
import spark.Route;

public class Usuarios_Controlador implements Route{

	public Object handle(Request req, Response resp) throws Exception {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.selectLogin(req.params("login"));
	}		
}