package distance;

import dicionario.Dicionario;

import java.io.*;

public class Levenshtein {

	public static String distance(String a) {
		String palavra = a.toLowerCase();
		Dicionario dicionario = new Dicionario();
		String[][] aproximadas = new String[3][2];
		String b = "";
		boolean verificaExcecao = false;
		if (dicionario.isCorrect(palavra)) {
			return null;
		} else {
			if(palavra.contains("a") || palavra.contains("o")){
				for(int i=1; i < palavra.length()-1; i++){
					if(palavra.charAt(i) == 'o'  && dicionario.isCorrect(replaceOne(palavra, i, 'o', 'õ'))){
						aproximadas[0][1] =  replaceOne(palavra, i, 'o', 'õ');
						aproximadas[0][0] = 0+"";
						verificaExcecao = true;
					}
					if(palavra.charAt(i) == 'a' && dicionario.isCorrect(replaceOne(palavra, i, 'a', 'ã'))){
						aproximadas[0][1] = replaceOne(palavra, i, 'a', 'ã');
						aproximadas[0][0] = 0+"";
						verificaExcecao = true;
					}
				}
			}
			
			
			for (int t = 0; t < dicionario.size(); t++) {
				b = dicionario.next();
				int[] costs = new int[b.length() + 1];
				for (int j = 0; j < costs.length; j++)
					costs[j] = j;
				for (int i = 1; i <= palavra.length(); i++) {
					costs[0] = i;
					int nw = i - 1;
					for (int j = 1; j <= b.length(); j++) {
						int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
								palavra.charAt(i - 1) == b.charAt(j - 1) ? nw
										: nw + 1);
						nw = costs[j];
						costs[j] = cj;
					}
				}
				for (int i = 0; i < 3; i++) {
					if (aproximadas[i][0] == null || Integer.parseInt(aproximadas[i][0]) > costs[b.length()]) {
						if(verificaExcecao == true && i != 0 && b != aproximadas[0][1] || verificaExcecao == false){
							aproximadas[i][1] = b;
							aproximadas[i][0] = costs[b.length()]+"";
						}
						break;
					}
				}
			}
		}

		return aproximadas[0][1] + " " + aproximadas[1][1] + " "
				+ aproximadas[2][1];
	}
	
	public static String replaceOne(String s, int i, char a, char b){
		char[] palavraNova =  s.toCharArray();
		if(palavraNova[i] == 'a' || palavraNova[i]=='o') palavraNova[i] = b;
		return String.valueOf(palavraNova);
		
	}
}
