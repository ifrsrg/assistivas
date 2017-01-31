package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class FileManager {

	public Boolean save(Part file, String tipo, int id, String nome, String timestamp){
						
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
	}
	
	public Boolean update(Part file, String tipo, int id, String nome, String timestamp){
		
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
	
	public Boolean verificaExt(Part file, int tipo){
		
		String conteudo = file.getContentType();
		
		switch(tipo){
			case 0: if (conteudo.equals("image/jpeg") ||
						conteudo.equals("image/png") ||
						conteudo.equals("application/octet-stream")) {
						return true;
					}else{
						return false;
					}
			case 1: if (conteudo.equals("video/mp4") || 
						conteudo.equals("video/avi") ||
						conteudo.equals("video/mkv") ||
						conteudo.equals("video/ogg") ||
						conteudo.equals("video/x-msvideo") ||
						conteudo.equals("application/octet-stream")){
						return true;
					}else{
						return false;
					}
		}
		return null;
	}
}
