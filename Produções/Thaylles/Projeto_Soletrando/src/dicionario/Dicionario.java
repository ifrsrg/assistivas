package dicionario;

import java.io.*;
import java.util.*;

public class Dicionario {
	
	private ArrayList<String> palavras = new ArrayList<String>();
	
	private int cont = 0;
	
	public Dicionario() {
		
		File dicionario = new File("src/dicionario/dicionario.txt");
		
		Scanner scan = null;
		try {
			scan = new Scanner(dicionario);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (scan.hasNextLine()) {
			palavras.add(scan.nextLine());
		}
		scan.close();
	}
	
	public boolean isCorrect (String palavra) {
		for(int index = 0; index < palavras.size(); index++) {
			if(palavra.equals(palavras.get(index)))
				return true;
		}
		return false;
	}
	
	public String next(){
		return palavras.get(cont++);
	}
	
	public int size() {
		return palavras.size();
	}
	
	public void startNext() {
		cont = 0;
	}
	
	
	
}
