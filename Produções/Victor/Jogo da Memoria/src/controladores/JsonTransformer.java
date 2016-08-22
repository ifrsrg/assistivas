package controladores;

import spark.ResponseTransformer;

import com.google.gson.Gson;

public class JsonTransformer implements ResponseTransformer {

	@Override
	public String render(Object model) throws Exception {
		Gson g = new Gson();
		return g.toJson(model);
	}

}
