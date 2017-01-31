package controladores;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import dao.ParDAO;
import modelos.Par;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import tools.FileManager;

public class EditarPar_Controlador {

	private final TemplateViewRoute mostrar = new Mostrar();
	private final Route salvar = new Salvar();
	
	public class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request req, Response resp) {
			ParDAO dao = new ParDAO();
			
			Par par = dao.selectByData(req.params("data"));
			
			HashMap dados = new HashMap();
			dados.put("par", par);
			
			if (req.queryMap("erro") != null) {
				dados.put("erro", req.queryParams("erro"));
			}
			
			return new ModelAndView(dados, "editarPar.html");
			
		}
		
	}
	
	public class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			
			MultipartConfigElement multipart = new MultipartConfigElement("pub");
			
			req.raw().setAttribute("org.eclipse.multipartConfig", multipart);
						
			Part image = req.raw().getPart("foto");
			Part video = req.raw().getPart("video");
						
			int id = req.session().attribute("user");
			
			String nome = req.queryMap("nome").value();
			Integer nivel = Integer.parseInt(req.queryMap("nivel").value());
			String data = req.params("data");
									
			Par par = new Par();
			par.setId(req.session().attribute("user"));
			par.setNome(nome);
			par.setNivel(nivel);
			par.setData(data);
			
			FileManager f = new FileManager();
			
			if (f.verificaExt(image, 0) &&
				f.verificaExt(video, 1)){
				
						
				f.update(image, "image", id, nome, data);
				f.update(video, "video", id, nome, data);
				
				String form_img = null;
				
				if (!image.getContentType().equals("application/octet-stream")) {
					form_img = image.getContentType().split("/")[1];
				}
				
				par.setForm_img(form_img);
				par.setForm_vid("ogg");
				ParDAO parDAO = new ParDAO();
					
				parDAO.update(par);
				resp.redirect("/meusUploads");
			
			}else{
				String erro = URLEncoder.encode("Formatos de arquivo incorretos", "UTF-8");
				resp.redirect("/editarPar/" + par.getData() + "?erro="+erro);
			}
			return null;
		}
		
	}
	
	public TemplateViewRoute getMostrar() {
		return mostrar;
	}
	public Route getSalvar() {
		return salvar;
	}
		

}