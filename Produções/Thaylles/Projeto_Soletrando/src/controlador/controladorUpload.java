package controlador;

import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import DAO.UploadDAO;
import controlador.*;
import controlador.controladorHome.Mostrar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class controladorUpload implements Route {

	public Object handle(Request req, Response resp) throws Exception {
		
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/publico");
		req.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
		Part file = req.raw().getPart("arquivo"); //file is name of the upload form
		System.out.println(file.getContentType());
		if(file.getContentType().equals("audio/mp3")){
			InputStream input = file.getInputStream();
			FileOutputStream output = new FileOutputStream("bin/publico/" + req.queryMap("nome").value() + ".mp3");
			int b = 0;
			while ((b = input.read()) >= 0) {
				output.write(b);
			}
			input.close();
			output.close();

		}
		
		UploadDAO.insertPalavras(req.queryMap("nome").value(), req.queryMap("nivel").integerValue(), "bin/publico/" + req.queryMap("nome").value() + ".mp3");
		
		resp.redirect("/login/home");
		return null;
	
	}
	public final TemplateViewRoute mostrar = new Mostrar();
	public class Mostrar implements TemplateViewRoute{

		public ModelAndView handle(Request arg0, Response arg1) {
			return new ModelAndView(null, "upload.html");
			}
		
		}
}
