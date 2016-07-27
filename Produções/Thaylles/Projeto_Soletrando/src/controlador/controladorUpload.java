package controlador;

import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class controladorUpload implements Route {

	@Override
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
		
		resp.redirect("/cadastro");
		return null;
	
	}
}