package web;

import com.google.gson.Gson;

import spark.*;

public class JsonTransformer 
	implements ResponseTransformer {

	private Gson gson = new Gson();
	
	@Override // {nome: "Marcio", idade: 39}
	public String render(Object o) throws Exception {
		return gson.toJson(o);
	}

}



