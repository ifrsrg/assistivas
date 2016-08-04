package controladores;

import java.util.HashMap;

import dao.UsuarioDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class Usuarios_Controlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		UsuarioDAO dao = new UsuarioDAO();
		HashMap dados = new HashMap();
		dados.put("nomes", dao.selectUsers(req.params("login")));
		return new ModelAndView(dados, "users.html");
	}
}