package concatenateWAV;

import java.io.*;
import java.util.*;

import javax.sound.sampled.*;

public class ConcatenateWAV {
	
	public static void soletrar(String palavra) {
		//char [] letras = palavra.toCharArray();
		ArrayList<String> temp = new ArrayList<String>();
		/*
		for(int index = 0; index < letras.length; index++) {
			switch (letras[index]) {      
	        	case '�': temp.add("a");temp.add("�");break;
	        	case '�': temp.add("a");temp.add("`");break;
	        	case '�': temp.add("a");temp.add("~");break;
	        	case '�': temp.add("a");temp.add("^");break;
	        	case '�': temp.add("e");temp.add("�");break;
	        	case '�': temp.add("e");temp.add("^");break;
	        	case '�': temp.add("i");temp.add("�");break;
	        	case '�': temp.add("o");temp.add("�");break; 
	        	case '�': temp.add("o");temp.add("~");break;
	        	case '�': temp.add("o");temp.add("^");break;
	        	case '�': temp.add("u");temp.add("�");break;  
	        	case '�': temp.add("c");temp.add("�");break;
	        	case '�': break;
	        	case '�': break;
	        	case '�': break;
	        	case '�': break;
	        	case '�': break;
	        	case '�': break;
	        	case '&': break;
	        	default: temp.add(letras[index] + "");
			}
		}
*/
		AudioInputStream audio = null;
				
		for(int index = 0; index < temp.size(); index++) {
			if(index == 0){
				try {
					audio = AudioSystem.getAudioInputStream(new File("Audios\\" + temp.get(index) + ".wav"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
			
				String dir = "Audios\\" + temp.get(index) + ".wav";
				audio = concatenate(audio, dir);
			}
		}
		
		try {
			File dir = new File("publico/palavrasSom/");
			if(dir.length() == 12)
				if(dir.delete())
					dir.mkdir();
			AudioSystem.write(audio, AudioFileFormat.Type.WAVE, new File("publico/palavrasSom/"+ palavra +".wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private static AudioInputStream concatenate(AudioInputStream wav1, String wavFile2) {
    	AudioInputStream appendedFiles = null;
	    try {
		    AudioInputStream wav2 = AudioSystem.getAudioInputStream(new File(wavFile2));

		    appendedFiles = new AudioInputStream(new SequenceInputStream(wav1, wav2), 
		    		wav1.getFormat(), wav1.getFrameLength() + wav2.getFrameLength());
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
	    return appendedFiles;
    }
}
