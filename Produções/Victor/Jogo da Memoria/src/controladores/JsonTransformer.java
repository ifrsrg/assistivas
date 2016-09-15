package controladores;

import com.google.gson.Gson;

import spark.ResponseTransformer;



public class JsonTransformer implements ResponseTransformer {

	@Override
	public String render(Object model) throws Exception {
		Gson g = new Gson();
		return g.toJson(model);
	}

}
