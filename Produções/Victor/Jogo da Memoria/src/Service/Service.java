package Service;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Service implements Route{

	public abstract Object deal(Request req, Response resp) throws Exception;
	
	public Object handle(Request req, Response resp) throws Exception{
		resp.header("Content-Type", "application/json");
		resp.header("Access-Control-Allow-Origin", "*");
		
		return this.deal(req, resp);
		
	}
}
