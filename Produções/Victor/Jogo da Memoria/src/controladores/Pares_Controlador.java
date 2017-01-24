package controladores;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import dao.ParDAO;
import modelos.Par;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import tools.FileManager;
import tools.Timestamp;

public class Pares_Controlador {
	
	private final Route salvar = new Salvar();
	private final TemplateViewRoute mostrar = new Mostrar();
	
	class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			
			MultipartConfigElement multipart = new MultipartConfigElement("pub");
						
			req.raw().setAttribute("org.eclipse.multipartConfig", multipart);
						
			Part image = req.raw().getPart("foto");
			Part video = req.raw().getPart("video");
						
			int id = req.session().attribute("user");
			
			String nome = req.queryMap("nome").value();
			Integer nivel = Integer.parseInt(req.queryMap("nivel").value());
			
			FileManager f = new FileManager();
			Timestamp t = new Timestamp();
			
			String time = t.getTimestamp();
			
			
			if (f.save(image, "image", id, nome, time) &&
				f.save(video, "video", id, nome, time)) {
				String form_img = image.getContentType().split("/")[1];
				String form_vid = video.getContentType().split("/")[1];
				Par par = new Par(req.session().attribute("user"), nome, time, form_img, form_vid, nivel);
				ParDAO parDAO = new ParDAO();
				parDAO.save(par);
				resp.redirect("/meusUploads");
			}else{
				String erro = URLEncoder.encode("Formatos de arquivo incorretos", "UTF-8");
				resp.redirect("/novoPar?erro="+erro);
			}
			return null;
		}
		
	}
	
	class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request req, Response resp) {
			if (req.queryMap("erro") != null) {
				HashMap dados = new HashMap();
				dados.put("erro", req.queryParams("erro"));
				return new ModelAndView(dados, "pares.html");
			}
			return new ModelAndView(null, "pares.html");
		}
		
	}

	public Route getRoute() {
		return salvar;
	}

	public TemplateViewRoute getTemplateView() {
		return mostrar;
	}
}