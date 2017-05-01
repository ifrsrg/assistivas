package controlador;

import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import DAO.UploadDAO;
import model.Upload;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class controladorGravar {
	
	public class salvar implements Route {
	public Object handle(Request req, Response resp) throws Exception {
		
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/publico");
		req.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
		Part file = req.raw().getPart("arquivo"); //file is name of the upload form
		System.out.println(file.getContentType());
		if(file.getContentType().equals("audio/mp3") || file.getContentType().equals("audio/waw")){
			InputStream input = file.getInputStream();
			FileOutputStream output = new FileOutputStream("bin/publico/" + req.queryMap("nome").value() + ".mp3");
			int b = 0;
			while ((b = input.read()) >= 0){
			output.write(b);
			}
			input.close();
			output.close();

		}
		System.out.println("bin/publico/" + req.queryMap("nome").value() + ".mp3");
		System.out.println(req.queryMap("nome").value());
		
		Upload palavra = new Upload();
		palavra.setPalavra(req.queryMap("nome").value());
		palavra.setCaminho("bin/publico/" + req.queryMap("nome").value() + ".mp3");
		palavra.setDificuldade(req.queryMap("nivel").integerValue());
		
		UploadDAO.insertPalavras(palavra);
		
		resp.redirect("/login/home");
		return null;
	
		}
	}
	public final TemplateViewRoute mostrar = new Mostra();
	public Route salvar;
	
	public class Mostra implements TemplateViewRoute{
	
		public ModelAndView handle(Request req, Response resp) {
		return new ModelAndView (null, "gravar.html");
		}
	}
}
