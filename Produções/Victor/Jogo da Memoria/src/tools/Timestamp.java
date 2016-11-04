package tools;

import java.util.Date;

public class Timestamp {

	@SuppressWarnings("deprecation")
	public String getTimestamp(){
		Date data = new Date();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(normatiza(data.getDate()));
		stringBuilder.append(normatiza(data.getMonth()+1));
		stringBuilder.append((data.getYear() + 1900));
		stringBuilder.append(normatiza(data.getHours()));
		stringBuilder.append(normatiza(data.getMinutes()));
		stringBuilder.append(normatiza(data.getSeconds()));
		return stringBuilder.toString();
	}
	
	protected String normatiza(int numero){
		if (numero < 10) 
			return "0"+numero;
		return numero+"";
	}

}
