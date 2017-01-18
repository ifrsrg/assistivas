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
										file.getContentType().equals("video/ogg")   ||
										file.getContentType().equals("video/x-msvideo")))) {
			try {
			
				InputStream input = file.getInputStream();
				nome = nome.replace(" ", "_");
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
		
		File dir = getDir(tipo);
		
		for  (File f : dir.listFiles()){
			String name_file = f.getName();
			if (name_file.contains(timestamp)) {
				String[] array = name_file.split("_");
				array[1] = nome.replaceAll(" ", "_");
				f.renameTo(new File(dir, array[0] + "_" + array[1] + "_" + array[2]));
			}
		}
				
		if (file.getContentType().equals("application/octet-stream")){
			return false;
		}
		
		delete(timestamp, tipo);
		return save(file, tipo, id, nome, timestamp);
	}
	
	private File getDir(String tipo){		
		if (tipo.equals("video")) {
			return new File("bin/pub/video/");
		}else{
			return new File("bin/pub/image/");
		}
	}
	
	public void delete(String timestamp, String tipo){
		
		File dir = getDir(tipo);
		
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
