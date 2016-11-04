package web2;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Service implements Route {

	public abstract Object deal(Request request, Response response) throws Exception;
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		// está voltando um json
		response.header("Content-Type", "application/json");
		// permitir o AJAX
		response.header("Access-Control-Allow-Origin", "*");
		return this.deal(request, response);
	}

}


