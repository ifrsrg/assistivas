package DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AdicionaDAO {
	
	public static void Salva(String texto){
		
		File dir = new File("publico/palavras/");
		if (!dir.exists()) dir.mkdir();
		
		File arquivo = new File("publico/palavras/" + dir.listFiles().length + ".txt");
		
		try {
			FileWriter writer = new FileWriter(arquivo);
			writer.write(texto);
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Deleta(){
		File arq = new File("publico/palavras/palavrasErradas.txt");
		if (arq.exists()) arq.delete();
		
	}
	
}
