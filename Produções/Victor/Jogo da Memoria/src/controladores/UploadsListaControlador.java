package controladores;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class UploadsListaControlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		return new ModelAndView(null, "uploads.html");
	}

}
