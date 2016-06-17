package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
	
	public static String load(int numero){
		File arq = new File("publico/palavras/"+numero+".txt");
		try {
			Scanner scan = new Scanner(arq);
			return scan.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return "";
	}
	
}
