package Service;

import spark.ResponseTransformer;

public class WebService {

	public final String contentType;
	public final ResponseTransformer responseTransformer;
	
	protected WebService(String contentType, ResponseTransformer responseTransformer){
		this.contentType = contentType;
		this.responseTransformer = responseTransformer;
	}
}
