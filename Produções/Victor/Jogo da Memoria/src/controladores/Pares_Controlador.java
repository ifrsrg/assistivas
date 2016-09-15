package controladores;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import modelos.Par;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import dao.ParDAO;

public class Pares_Controlador {
	
	private final Route salvar = new Salvar();
	private final TemplateViewRoute mostrar = new Mostrar();
	
	class Salvar implements Route{

		@Override
		public Object handle(Request req, Response resp) throws Exception {
			
			MultipartConfigElement multipart = new MultipartConfigElement("/pub");
						
			req.raw().setAttribute("org.eclipse.multipartConfig", multipart);
						
			Part image = req.raw().getPart("foto");
			Part video = req.raw().getPart("video");
						
			int id = req.session().attribute("user");
			
			String nome = req.queryMap("nome").value();
			Integer nivel = Integer.parseInt(req.queryMap("nivel").value());
			
			String time = getTimestamp();
			
			if (SalvaInputs(image, "image", id, nome, time) &&
				SalvaInputs(video, "video", id, nome, time)) {
				String form_img = image.getContentType().split("/")[1];
				String form_vid = video.getContentType().split("/")[1];
				Par par = new Par(req.session().attribute("user"), nome, time, form_img, form_vid, nivel);
				ParDAO parDAO = new ParDAO();
				parDAO.save(par);
				resp.redirect("/menu");
			}else{
				String erro = URLEncoder.encode("Formatos de arquivo incorretos", "UTF-8");
				resp.redirect("/novoPar?erro="+erro);
			}
			return null;
		}
		
		
		public boolean SalvaInputs(Part file, String tipo, int id, String nome, String time){
			if (((tipo.equals("image")) && (file.getContentType().equals("image/jpeg") ||
										    file.getContentType().equals("image/png"))) ||
				((tipo.equals("video")) && (file.getContentType().equals("video/mp4")  ||
											file.getContentType().equals("video/mkv")  ||
											file.getContentType().equals("video/avi")))) {
				try {
					
					InputStream input = file.getInputStream();
					FileOutputStream output = new FileOutputStream("bin/pub/" + tipo + "/" + id + "_" + nome + "_" + time + "." + file.getContentType().split("/")[1]);
					for (int i = input.read(); i >= 0; i = input.read()) {
						output.write(i);
					}
					input.close();
					output.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} return false;
		}
		
		@SuppressWarnings("deprecation")
		public String getTimestamp(){
			Date data = new Date();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(data.getDay());
			stringBuilder.append(normatiza(data.getDate()));
			stringBuilder.append(normatiza(data.getMonth()+1));
			stringBuilder.append((data.getYear() + 1900));
			stringBuilder.append(normatiza(data.getHours()));
			stringBuilder.append(normatiza(data.getMinutes()));
			stringBuilder.append(normatiza(data.getSeconds()));
			return stringBuilder.toString();
		}
		
		public String normatiza(int numero){
			if (numero < 10) 
				return "0"+numero;
			return numero+"";
		}
		
	}
	
	class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request req, Response resp) {
			String erro = req.queryParams("erro");
			if (erro != null) {
				HashMap dados = new HashMap();
				dados.put("erro", erro);
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