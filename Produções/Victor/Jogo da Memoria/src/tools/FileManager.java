package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class FileManager {

	public boolean save(Part file, String tipo, int id, String nome, String timestamp){
				
		if (((tipo.equals("image")) && (file.getContentType().equals("image/jpeg")  ||
									    file.getContentType().equals("image/png"))) ||
			((tipo.equals("video")) && (file.getContentType().equals("video/mp4")   ||
										file.getContentType().equals("video/mkv")   ||
										file.getContentType().equals("video/avi")   ||
										file.getContentType().equals("video/ogg")))) {
			try {
			
				InputStream input = file.getInputStream();
				String nome_file = id + "_" + nome + "_" + timestamp + "." + file.getContentType().split("/")[1];
				FileOutputStream output = new FileOutputStream("bin/pub/" + tipo + "/" + nome_file);
				for (int i = input.read(); i >= 0; i = input.read()) {
					output.write(i);
				}
				input.close();
				output.flush();
				output.close();
				if (tipo.equals("video"))
					converte(nome_file);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} return false;
	}
	
	public boolean update(Part file, String tipo, int id, String nome, String timestamp){
		if (file.getContentType().equals("application/octet-stream"))
			return false;
		delete(timestamp, tipo);
		return save(file, tipo, id, nome, timestamp);
	}
	
	public void delete(String timestamp, String tipo){
		File dir;
		
		if (tipo.equals("video")) {
			dir = new File("bin/pub/video/");
		}else{
			dir = new File("bin/pub/image/");
		}
		
		for (File file : dir.listFiles()) {
			if (file.getName().contains(timestamp)) {
				file.delete();
			}
		}
	}
	
	protected void converte(String nome_file){
		
		File f = new File("bin/pub/video/"+nome_file);
		f.canExecute();
		File dir = f.getParentFile();
		String nome = nome_file.substring(0, nome_file.length()-4);
		String s = "ffmpeg -i " + nome_file + " " + nome + ".ogg";
		
		try {
			Process p = Runtime.getRuntime().exec(s, null, dir);
			p.waitFor();
			f.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
