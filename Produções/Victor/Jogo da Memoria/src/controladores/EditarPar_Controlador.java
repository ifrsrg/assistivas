package controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import dao.ParDAO;
import modelos.Par;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class EditarPar_Controlador {

	private final TemplateViewRoute mostrar = new Mostrar();
	private final Route salvar = new Salvar();
	
	public class Mostrar implements TemplateViewRoute{

		@Override
		public ModelAndView handle(Request req, Response resp) {
			ParDAO dao = new ParDAO();
			
			Par par = dao.selectByData(req.params("data"));
			return new ModelAndView(par, "editarPar.html");
			
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
			SalvaInputs(image, "image", id, nome, data);
			SalvaInputs(video, "video", id, nome, data);
			
			String form_img = "jpeg";
			
			if (!image.getContentType().equals("application/octet-stream")) {
				System.out.println(image.getContentType());
				form_img = image.getContentType().split("/")[1];
			}
			
			par.setForm_img(form_img);
			par.setForm_vid("ogg");
			
			ParDAO parDAO = new ParDAO();
				
			if (parDAO.delete(data) == false) {
				parDAO.save(par);
			};
			resp.redirect("/menu");
			return null;
			
			}
		}
		
		
		public boolean SalvaInputs(Part file, String tipo, int id, String nome, String time){
			
			if (file != null) {
				
				if (((tipo.equals("image")) && (file.getContentType().equals("image/jpeg")  ||
											    file.getContentType().equals("image/png"))) ||
					((tipo.equals("video")) && (file.getContentType().equals("video/mp4")   ||
												file.getContentType().equals("video/mkv")   ||
												file.getContentType().equals("video/avi")   ||
												file.getContentType().equals("video/ogg")))) {
					try {
						
						deleteOldFiles("bin/pub/"+tipo, time);
						
						InputStream input = file.getInputStream();
						String nome_file = id + "_" + nome + "_" + time + "." + file.getContentType().split("/")[1];
						FileOutputStream output = new FileOutputStream("bin/pub/" + tipo + "/" + nome_file);
						for (int i = input.read(); i >= 0; i = input.read()) {
							output.write(i);
						}
						input.close();
						output.close();
						/*if (tipo.equals("video"))
							converte(nome_file);*/
						return true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} return false;
		}
		
		public void deleteOldFiles(String pasta, String timestamp){
			File f = new File("bin/pub/"+pasta);
			String[] array = f.list();
			
			//ERRO NO ARRAY
			
			for (String item : array) {
				if (item.endsWith(timestamp)) {
					new File("bin/pub/" + pasta + "/" + item).delete();
				}
			}
			
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
		
		//Falta implementar
		
		protected void converte(String nome_file){
			File f = new File("bin/pub/video/"+nome_file);
			String nome = nome_file.substring(0, nome_file.length()-4);
			String s = "ffmpeg -i " + nome_file + " " + nome + ".ogg";
			try {
				//Runtime.getRuntime().exec("cd bin/pub/video");
				Runtime.getRuntime().exec(s);
				f.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public TemplateViewRoute getMostrar() {
		return mostrar;
	}
	public Route getSalvar() {
		return salvar;
	}
		
}