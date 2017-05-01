package web2;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class MainWeb {

	public static void main(String[] args) {
		
		ContatosWebService ws = 
				new ContatosWebService();
		
		Spark.get("/upload/:palavra", ws.contentType, ws.selectRota, ws.responseTransformer);
		
		Spark.post("/upload", ws.insert);
		
		
	}
}







